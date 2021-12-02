import axios from "axios"
import jwt_decode from 'jwt-decode'
import React, { useState, useEffect } from "react"
import './StudentBioView.css';
const StudentBioView = () => {
    const user = localStorage.getItem("jwtToken")
    let gradYearId = localStorage.getItem("guestChosenGradYear")
    const [StudentBios, setStudentBios] = useState([])

    useEffect(() => {
        axios.get(`student/getAll/studentBio/${gradYearId}`, {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                console.log(res.data)
                setStudentBios(res.data)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [])

    return (
        <div id="studentBioViewContainer">
            {StudentBios.map((item) =>
                <div class="studentBios">
                    <div class = "studentLogo">
                        <img src={process.env.PUBLIC_URL + "./Student.png"} width="110px" alt="scndlogo" />
                    </div>
                    <div class="firstFive">
                        <h3> FullName: {item.fullName}</h3>
                        <h3>NickName: {item.nickName}</h3>
                        <h3>DOB: {item.dateOfBirth}</h3>
                        <h3>Hobbies: {item.hobbies}</h3>
                        <h3>RoleModel: {item.roleModel}</h3>
                    </div>

                    <div class="lastFive">
                        <h3>School Starting Year: {item.schoolStartingYear}</h3>
                        <h3>State of Origin: {item.stateOfOrigin}</h3>
                        <h3>Favorable Quote: {item.favorableQuote}</h3>
                        <h3>Memorable Day: {item.memorableDay}</h3>
                        <h3>Farewell Message: {item.farewellMsg}</h3>
                    </div>
                </div>
            )}
        </div >
    );
}

export default StudentBioView;