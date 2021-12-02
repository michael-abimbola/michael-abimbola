import axios from "axios"
import React, { useState, useEffect, useContext } from "react"
const StudentSignUp = () => {
    const user = localStorage.getItem("jwtToken")
    const [count, setCount] = useState(0)

    const [gradYears, setGradYears] = useState([])
    const [gradClasses, setGradClasses] = useState([])

    const [success, setSuccess] = useState(false)

    const [Student, setStudent] = useState({
        username: "",
        profileName: "",
        pwd: "",
        role: "STUDENT",
        graduatingYear: {
            id: 0
        },
        graduatingClass: {
            id: 0
        }
    })

    useEffect(() => {
        axios.get("graduatingyear", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setGradYears(res.data)
                console.log(res.data)
            })
            .catch((err) => {
                console.log(err + "gradyear error")
            })
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
        if (Student.username !== "" || Student.profileName !== "" || Student.pwd !== ""
            || Student.role == "STUDENT" || Student.graduatingClass.id != 0 || Student.graduatingYear.id != 0
            || Student.graduatingClass.id != "GradClasses" || Student.graduatingYear.id != "GradYears") {
            axios.post("student", Student, {
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
        console.log("hello")
        alert("sent")
    })

    return (
        <div className="form-container sign-in-container" id="showTwo">
            <form onSubmit={handleSubmit}>
                <h1>Sign up</h1>
                <input
                    type="text"
                    placeholder="student user ID"
                    required
                    value={Student.username}
                    onChange={(e) => setStudent({ ...Student, username: e.target.value })} />
                <input
                    type="text"
                    placeholder="student name"
                    required
                    value={Student.profileName}
                    onChange={(e) => setStudent({ ...Student, profileName: e.target.value })} />
                <select onChange={(e) => setStudent({ ...Student, graduatingYear: { id: e.target.value } })}>
                    <option>GradYears</option>
                    {gradYears.map((item) =>
                        <option value={item.id}>{item.graduatingYear}</option>)}
                </select>

                <select onChange={(e) => setStudent({ ...Student, graduatingClass: { id: e.target.value } })}>
                    <option>GradClasses</option>
                    {gradClasses.map((item) =>
                        <option value={item.id}>{item.className}</option>)}
                </select>
                <input
                    type="password"
                    placeholder="password"
                    required
                    value={Student.pwd}
                    onChange={(e) => setStudent({ ...Student, pwd: e.target.value })} />


                <button type="submit">Register Student</button>
                {success && <h4 className="success">Student successfully registered!</h4>}
            </form>
        </div>
    );
}

export default StudentSignUp;