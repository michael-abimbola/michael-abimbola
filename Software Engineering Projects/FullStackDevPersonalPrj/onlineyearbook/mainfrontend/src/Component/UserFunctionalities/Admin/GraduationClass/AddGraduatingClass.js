import axios from 'axios'
import React, { useState, useEffect } from 'react'
import './GraduatingClass.css';
const AddGraduatignClass = () => {
    const [GraduatingClass, setGraduatingClass] = useState({
        className: ""
    })
    const [count, setCount] = useState(0)
    const [success, setSuccess] = useState(false)

    const user = localStorage.getItem("jwtToken")
    useEffect(() => {
        if(GraduatingClass.className != ""){
            axios.post("graduatingclass", GraduatingClass, {
                headers:{
                    "Authorization": `Bearer ${user}`
                }
            })
            .then((res) => {
                setSuccess(true)
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
        }
    }, [count])

    const handleSubmit = ((e) => {
        alert(`${GraduatingClass.className}`)
        setCount(count + 1)
        e.preventDefault()
    })


    return ( 
        <div className="container">
            <div className="form-container sign-in-container" id = "gradClassForm">
            <form onSubmit={handleSubmit}>
            <h1 className = "gradTitle">Add Graduation Class</h1>
                <div>
                    <input className="inputGradYear"
                        type="text"
                        placeholder="Example:classA"
                        required
                        name="gradClass"
                        value={GraduatingClass.className}
                        onChange={(e) => setGraduatingClass({...GraduatingClass, className: e.target.value})} />
                </div>
                <button type="submit">Add Class</button>
                {success && <h4 className="success">Graduating Class successfully added!</h4>}
            </form>
            </div>
{/* 
            <h1 className="graduationViewTitle">Graduation years</h1>
            <FetchGradYears /> */}
        </div>
    )
}
 
export default AddGraduatignClass;