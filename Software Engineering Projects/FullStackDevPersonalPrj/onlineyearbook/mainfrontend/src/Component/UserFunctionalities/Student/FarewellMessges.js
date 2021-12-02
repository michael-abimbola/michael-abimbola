import axios from "axios"
import jwt_decode from 'jwt-decode'
import './FarewellMessageVIew.css';
import React, { useState, useEffect, useContext } from "react"
const FarewellMessages = () => {
    const user = localStorage.getItem("jwtToken")
    let decoded = jwt_decode(user)
    localStorage.setItem("usr", decoded.sub)

    const username = localStorage.getItem("usr")
    const [farewellMessages, setFareWellMessages] = useState([])

    useEffect(() => {
        axios.get(`student/getAll/farewellMessages/${username}`, {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setFareWellMessages(res.data)
                console.log(res.data)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [])

    return (
        <div id="farewellMessageViewPageContainer">
            <div class="title">
                <h1>Farewell Message Page</h1>
            </div>
            {farewellMessages.map((item) => 
                <div class = "farewellMsgContainer">
                    <div class="classRoomLogo">
                        <img src={process.env.PUBLIC_URL + "./teacher.png"} width="120px" alt="scndlogo" />
                    </div>

                    <div class = "farewellMsgText">
                        <h3>{item.msg}</h3>
                    </div>
                </div>
            )}
        </div>
    );
}

export default FarewellMessages;