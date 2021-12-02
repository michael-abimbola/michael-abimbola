import './FarewellMessage.css';
import axios from "axios"
import jwt_decode from 'jwt-decode'
import React, { useState, useEffect, useContext } from "react"
const FarewellMessage = () => {
    const user = localStorage.getItem("jwtToken")
    const [className, setClassName] = useState("")
    const [gradClasses, setGradClasses] = useState([])
    const [success, setSuccess] = useState(false)
    const [count, setCount] = useState(0)
    const [FarewellMessage, setFarewellMessage] = useState({
        msg: ""
    })
    let decoded = jwt_decode(user)
    localStorage.setItem("usr", decoded.sub)

    const username = localStorage.getItem("usr")

    useEffect(() => {
        axios.get("graduatingclass", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setGradClasses(res.data)
                console.log(FarewellMessage)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [])

    useEffect(() => {
        if (FarewellMessage.msg !== "") {
            axios.post(`teacher/farewellmsg/${username}/${className}`, FarewellMessage, {
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
        <div id="farewellMessagePageContainer">
            <div class="title">
                <h1>Farewell Message Page</h1>
            </div>
            {success && <h4 className="success">Farewell Message Added!</h4>}
            {gradClasses.map((item) =>
                <div class="gradClasses">
                    <div class="classRoomLogo">
                        <img src={process.env.PUBLIC_URL + "./classroom.png"} width="120px" alt="scndlogo" />
                    </div>

                    <div class="gradClassName">
                        <h1>Class {item.className}</h1>
                    </div>

                    <div class="farewellMsgInput">
                        <input
                            autoComplete="off"
                            type="text"
                            size="60"
                            onChange={(e) => setFarewellMessage({ ...FarewellMessage, msg: e.target.value })}
                            onInput={() => setClassName(item.className)}
                        />
                    </div>

                    <div class="farewellMsgAddBtn">
                        <button onClick={handleSubmit}>Send FarewellMessage</button>
                    </div>
                </div>
            )}

        </div>
    );
}

export default FarewellMessage;