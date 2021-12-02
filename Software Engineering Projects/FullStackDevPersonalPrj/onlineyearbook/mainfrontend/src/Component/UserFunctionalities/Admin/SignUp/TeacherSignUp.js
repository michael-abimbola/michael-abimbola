import axios from "axios"
import React, { useState, useEffect } from "react"
const TeacherSignUp = () => {
    const user = localStorage.getItem("jwtToken")
    const [success, setSuccess] = useState(false)
    const [gradClasses, setGradClasses] = useState([])
    const [count, setCount] = useState(0)
    const [Teacher, setTeacher] = useState({
        username: "",
        profileName: "",
        pwd: "",
        role: "TEACHER",
        graduatingClass: {
            id: 0
        }
    })
    useEffect(() => {
        axios.get("graduatingclass", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setGradClasses(res.data)
            })
            .catch((err) => {
                console.log(err)
            })
    })

    useEffect(() => {
        if (Teacher.username !== "" || Teacher.profileName !== "" || Teacher.pwd !== "" || Teacher.role == "TEACHER"
            || Teacher.graduatingClass.id != 0 || Teacher.graduatingClass.id != "GradClasses") {
            axios.post("teacher", Teacher, {
                headers: {
                    "Authorization": `Bearer ${user}`
                }
            })
                .then((res) => {
                    console.log(res)
                    setSuccess(true)
                })
                .catch((err) => {
                    console.log(err)
                })
        }
    }, [count])


    const handleSubmit = ((e) => {
        setCount(count + 1)
        e.preventDefault()
        alert("registering teacher")
    })

    return (
        <div className="form-container sign-in-container" id="showThree">
            <form onSubmit={handleSubmit}>
                <h1>Sign up</h1>
                <input
                    type="text"
                    placeholder="teacher user ID"
                    required
                    value={Teacher.username}
                    onChange={(e) => setTeacher({ ...Teacher, username: e.target.value })} />
                <input
                    type="text"
                    placeholder="teacher name"
                    required
                    value={Teacher.profileName}
                    onChange={(e) => setTeacher({ ...Teacher, profileName: e.target.value })} />

                <select onChange={(e) => setTeacher({ ...Teacher, graduatingClass: { id: e.target.value } })}>
                    <option>GradClasses</option>
                    {gradClasses.map((item) =>
                        <option value={item.id}>{item.className}</option>)}
                </select>
                <input
                    type="password"
                    placeholder="password"
                    required
                    value={Teacher.pwd}
                    onChange={(e) => setTeacher({ ...Teacher, pwd: e.target.value })} />
                <button type="Submit">Register Teacher</button>
                {success && <h4 className="success">Teacher successfully registered!</h4>}
            </form>
        </div>
    )
}

export default TeacherSignUp;