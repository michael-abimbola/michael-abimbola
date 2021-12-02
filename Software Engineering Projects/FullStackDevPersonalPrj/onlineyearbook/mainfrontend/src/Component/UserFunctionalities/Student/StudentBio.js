import axios from 'axios';
import { useEffect, useState } from 'react';
import jwt_decode from 'jwt-decode'
import './StudentBio.css';
const StudentBio = () => {
    const [Bio, setBio] = useState({
        fullName: "",
        dateOfBirth: "",
        nickName: "",
        schoolStartingYear: "",
        roleModel: "",
        stateOfOrigin: "",
        hobbies: "",
        favorableQuote: "",
        memorableDay: "",
        farewellMsg: ""
    })
    const [success, setSuccess] = useState(false)
    const [count, setCount] = useState(0)

    const user = localStorage.getItem("jwtToken")

    let decoded = jwt_decode(user)
    localStorage.setItem("usr", decoded.sub)

    const username = localStorage.getItem("usr")

    useEffect(() => {
        if(Bio.fullName !== ""){
            axios.put(`student/studentbio/${username}`, Bio, {
                headers: {
                    "Authorization": `Bearer ${user}`
                }
            })
            .then((res) => {
                console.log(res)
                setSuccess(true)
            })
            .catch(() => {
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
        <div id="studentBioContainer">
            <div class="studentbioBox">
                <div class="studentBioText">
                    <h1>
                        Student Bio
                    </h1>
                </div>
                <form class="studentBioInterface" onSubmit = {handleSubmit}>
                    <div class="firstFive">
                        <div class = "insidefive">
                            <h1>Full name</h1>
                            <input 
                            autoComplete = "off" 
                            type="text"
                            value = {Bio.fullName}
                            onChange = {(e) => setBio({...Bio, fullName: e.target.value})}/>
                            <h1>DOB</h1>
                            <input 
                            autoComplete = "off"
                            type="text"
                            value = {Bio.dateOfBirth}
                            onChange = {(e) => setBio({...Bio, dateOfBirth: e.target.value})}/>
                            <h1>Nickname</h1>
                            <input 
                            autoComplete = "off"
                            type="text"
                            value = {Bio.nickName}
                            onChange = {(e) => setBio({...Bio, nickName: e.target.value})}/>
                            <h1>School Starting Year</h1>
                            <input 
                            autoComplete = "off"
                            type="text"
                            value = {Bio.schoolStartingYear}
                            onChange = {(e) => setBio({...Bio, schoolStartingYear: e.target.value})}/>
                            <h1>Role Model</h1>
                            <input 
                            autoComplete = "off"
                            type="text"
                            value = {Bio.roleModel}
                            onChange = {(e) => setBio({...Bio, roleModel: e.target.value})}/>
                        </div>

                    </div>

                    <div class="lastFive">
                        <h1>Place of origin</h1>
                        <input 
                        autoComplete = "off"
                        type = "text"
                        value = {Bio.stateOfOrigin}
                        onChange = {(e)=>setBio({...Bio, stateOfOrigin: e.target.value})}/>
                        <h1>Hobbies</h1>
                        <input 
                        autoComplete = "off"
                        type = "text"
                        value = {Bio.hobbies}
                        onChange = {(e)=>setBio({...Bio, hobbies: e.target.value})}/>
                        <h1>Favorrite Quote</h1>
                        <input 
                        autoComplete = "off"
                        type = "text"
                        value = {Bio.favorableQuote}
                        onChange = {(e)=>setBio({...Bio, favorableQuote: e.target.value})}/>
                        <h1>Most Memorable day</h1>
                        <input 
                        autoComplete = "off"
                        type = "text"
                        value = {Bio.memorableDay}
                        onChange = {(e)=>setBio({...Bio, memorableDay: e.target.value})}/>
                    </div>

                    <div class = "fareWellMSgText">
                    <h1>Farewell Message</h1>
                        <textarea 
                        autoComplete = "off"
                        type = "text"
                        value = {Bio.farewellMsg}
                        onChange = {(e)=>setBio({...Bio, farewellMsg: e.target.value})}></textarea>
                    </div>

                    <div class = "createBio">
                    <button type= "submit">Create Bio</button>
                    </div>
                    {success && <h4 className="success">Student Bio successfully added!</h4>}
                </form>
            </div>
        </div>
    );
}

export default StudentBio;