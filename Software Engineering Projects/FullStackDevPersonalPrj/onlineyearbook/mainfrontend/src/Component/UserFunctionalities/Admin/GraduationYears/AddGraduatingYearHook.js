import axios from 'axios'
import React, { useState, useEffect } from 'react'
import FetchGradYears from './FetchGradYears'
import './GraduatingYear.css';

const AddGraduatingYearHooks = () => {
    const [GraduatingYear, setGraduatingYear] = useState({
        graduatingYear: 0
    })
    const [count, setCount] = useState(0)
    const [success, setSuccess] = useState(false)

    const user = localStorage.getItem("jwtToken")
    useEffect(() => {
        if(GraduatingYear.graduatingYear != 0){
            axios.post("graduatingyear", GraduatingYear, {
                headers: {
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

    //this is to ensure the post method only works when the count is increased 
    const handleSubmit = ((e) => {
        alert(`${GraduatingYear.graduatingYear}`)
        setCount(count + 1)
        e.preventDefault()
    })

    return (
        <div className="container">
            <div className="form-container sign-in-container">
                <form onSubmit={handleSubmit}>
                    <h1 className="gradTitle">Add GraduationYear</h1>
                    <input
                        type="text"
                        placeholder="Example:2030"
                        required
                        name="gradYear"
                        value={GraduatingYear.graduatingYear}
                        onChange={(e) => setGraduatingYear({ ...GraduatingYear, graduatingYear: e.target.value })} />

                    <button type="submit">Add year</button>
                    {success && <h4 className="success">Graduation Year successfully added!</h4>}
                </form>
                
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right" id="fetchDataOverlay">
                        <h1>Graduation years</h1>
                        <FetchGradYears />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddGraduatingYearHooks;