import axios from "axios"
import React, {useState, useEffect} from "react"
const YBCMSignUp = () => {
    const [YBCM, setYBCM] = useState({
        username: "",
        profileName: "",
        pwd: "", 
        role: "YBCM"
    })
    const [count, setCount] = useState(0)
    const [success, setSuccess] = useState(false)

    const user = localStorage.getItem("jwtToken")

    useEffect(() => {
        if(YBCM.username !== "" || YBCM.profileName !== "" || YBCM.pwd !== "" || YBCM.role !== "YBCM"){
            axios.post("ybcm", YBCM, {
                headers:{
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
    })

    return ( 
        <div className="form-container sign-in-container" id="showFour">
        <form onSubmit = {handleSubmit}>
        <h1>Sign up</h1>
            <input
                type="text"
                placeholder="yearbook committee member user ID" 
                required
                value = {YBCM.username}
                onChange = {(e) => setYBCM({...YBCM, username: e.target.value})}/>
            <input
                type="text"
                placeholder="yearbook committee member name" 
                required
                value = {YBCM.profileName}
                onChange = {(e) => setYBCM({...YBCM, profileName: e.target.value})}/>
            <input
                type="password"
                placeholder="password" 
                required
                value = {YBCM.pwd}
                onChange = {(e) => setYBCM({...YBCM, pwd:e.target.value})}/>
            <button type="Submit">Register YBCM</button>
            {success && <h4 className = "success">YBCM successfully registered!</h4>}
        </form>    
    </div>
    )
}
 
export default YBCMSignUp;