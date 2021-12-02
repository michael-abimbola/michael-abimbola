import axios from 'axios';
import { useEffect, useState } from 'react';
import jwt_decode from 'jwt-decode'
import './UpdateProfile.css';
const UpdateProfile = () => {

    const [profileName, setProfileName] = useState("")
    const [pwd, setPwd] = useState("")

    const [success, setSuccess] = useState(false)
    const [error, setError] = useState(false)

    const user = localStorage.getItem("jwtToken")

    let decoded = jwt_decode(user)
    localStorage.setItem("usr", decoded.sub)

    let gottenUsr = localStorage.getItem("usr")

    useEffect(() => {
        axios.get(`profile/loggedInUser/${gottenUsr}`, {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
        .then((res)=> {
            setProfileName(res.data.profileName)
        })
        .catch((err)=>{
            console.log(err)
        })
    }, [])

    const handleSubmit = ((e) => {
        e.preventDefault()
        fetch(
            `http://localhost:8080/profile/?username=${gottenUsr}&profileName=${profileName}&pwd=${pwd}`,
            {
                method: "PUT",

                headers: {
                    Authorization: `Bearer ${user}`,
                    "Access-Control-Allow-Origin": "*",
                    "Content-Length": "0",
                },
            }
        )
            .then((res) => {
                if (res.status === 202) {
                    setSuccess(true)
                    setError(false)
                }
                else {
                    console.log(res)
                    setError(true)
                    setSuccess(false)
                }

            })
            .catch((error) => {
                console.log(error);
            });
    })

    return (
        <div id="updateProfileContainer">
            <div class="updateProfileBox">
                <div class = "title">
                    <h1>Update Profile Info</h1>
                </div>
                <form class="updateProfileInterface" onSubmit={handleSubmit}>
                    <div class="interfaceInputs">
                        <h1>Profile name</h1>
                        <input
                            autoComplete="off"
                            type="text"
                            size = "50"
                            value = {profileName}
                            onChange={(e) => setProfileName(e.target.value)} />
                        <h1>Password</h1>
                        <input
                            type="password"
                            size = "50"
                            required
                            onChange={(e) => setPwd(e.target.value)} />
                    </div>
                    <div class="updateProfileBtn">
                        <button type="submit">Update Profile</button>
                    </div>
                    <div class = "messages">
                    {success && <h4 className="success">Student Bio successfully added!</h4>}
                    {error && <h4 className="error">Student Bio could not be updated!</h4>}
                    </div>
                </form>
            </div>
        </div>
    );
}

export default UpdateProfile;