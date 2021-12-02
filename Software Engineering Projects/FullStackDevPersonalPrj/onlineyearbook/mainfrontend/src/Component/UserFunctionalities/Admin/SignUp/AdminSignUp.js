import axios from "axios"
import React, { useState, useEffect } from "react"
const AdminSignUp = () => {
    const [AdminDTO, setAdmin] = useState({
        username: "",
        profileName: "",
        pwd: "",
        role: "ADMIN"
    })
    const [count, setCount] = useState(0)
    const [success, setSuccess] = useState(false)
    const [error, setError] = useState(false)

    const user = localStorage.getItem("jwtToken")

    useEffect(() => {
        if (AdminDTO.username !== "" && AdminDTO.profileName !== "" && AdminDTO.pwd !== "" && AdminDTO.role === "ADMIN") {
            axios.post("admin/", AdminDTO, {
                headers: {
                    "Authorization": `Bearer ${user}`
                }
            })
            .then((res) => {
                console.log(res)
                setSuccess(true)
            })
            .catch((err) => {
                // console.log(err)
                setSuccess(false)
            })
        }
    }, [count])

    const handleSubmit = ((e) => {
        setCount(count + 1)
        e.preventDefault()
        alert("sent")
    })
    return (
        <div className="form-container sign-in-container" id="showOne">
            <form onSubmit={handleSubmit}>
                <h1>Sign up</h1>
                <input
                    type="text"
                    placeholder="admin user ID"
                    required
                    value={AdminDTO.username}
                    onChange={(e) => setAdmin({ ...AdminDTO, username: e.target.value })} />
                <input
                    type="text"
                    placeholder="admin name"
                    required
                    value={AdminDTO.profileName}
                    onChange={(e) => setAdmin({ ...AdminDTO, profileName: e.target.value })} />
                <input
                    type="password"
                    placeholder="password"
                    required
                    value={AdminDTO.pwd}
                    onChange={(e) => setAdmin({ ...AdminDTO, pwd: e.target.value })} />
                <button type="Submit">Register Admin</button>
                {success && <h4 className="success">Admin successfully registered!</h4>}
            </form>
        </div>
    )
}

export default AdminSignUp;