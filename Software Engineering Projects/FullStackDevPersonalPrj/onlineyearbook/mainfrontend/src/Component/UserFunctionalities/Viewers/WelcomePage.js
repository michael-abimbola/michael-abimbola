import './WelcomePage.css';
import axios from "axios"
import React, { useState, useEffect } from "react"
import NavBar from "../../NavigationBar/NavBar";
import { BrowserRouter as Router, Route, Switch, useHistory } from 'react-router-dom';
import StudentBioView from './ImageGelleries/StudentBioView';
import TopStudentsOfTheYear from './ImageGelleries/TopStudentsOfTheYear';
import StudentAwards from './ImageGelleries/StudentAwards';
import StudentMemorableDays from './ImageGelleries/StudentMemorableDays';
import SportAwards from './ImageGelleries/SportAwards';

export const ChosenGradYearContext = React.createContext()
const WelcomePage = () => {
    const user = localStorage.getItem("jwtToken")
    const [gradYearId, setGradYearID] = useState(0)
    const [gradYears, setGradYears] = useState([])
    const [clicked, setClicked] = useState(false)
    const history = useHistory();
    useEffect(() => {
        axios.get("graduatingyear", {
            headers: {
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                setGradYears(res.data)
            })
            .catch((err) => {
                console.log(err)
            })
    })

    const handleSubmit = (e) => {
        setClicked(true)
        e.preventDefault()
        history.push("/studentBioView")
        localStorage.setItem("guestChosenGradYear", gradYearId)
    }
    return (
        <div id="landingPage">
            {!clicked && <div class="landingPageTextContainer">
                <div class="lightbar"></div>
                <div class="topLayer"></div>
                <h1>Welcome to MCSS Onlineyearbook</h1>
            </div>}

            {!clicked && <div class="gradYearContainer">
                <div class="desiredGradYearText">
                    <h2>Select desired gradution year to view</h2>
                </div>

                <div class="desiredGradYearCombo">
                    <select onChange={(e) => setGradYearID(e.target.value)}>
                        <option>GradYears</option>
                        {gradYears.map((item) =>
                            <option value={item.id}>{item.graduatingYear}</option>)}
                    </select>
                </div>
                <div class="desiredGradYearBtn">
                    <button onClick={handleSubmit}>Accepted</button>
                </div>
            </div>}
            {
                clicked && (
                    <Router>
                        <NavBar />
                        <Switch>
                            <Route exact path="/studentBioView">
                                <StudentBioView />
                            </Route>
                            <Route exact path="/sportAwards">
                                <SportAwards />
                            </Route>
                            <Route exact path="/topStudents">
                                <TopStudentsOfTheYear />
                            </Route>
                            <Route exact path="/studentAwards">
                                <StudentAwards />
                            </Route>
                            <Route exact path="/studentsMemorableDays">
                                <StudentMemorableDays />
                            </Route>
                            <Route exact path="/sportAwards">
                                <SportAwards />
                            </Route>
                        </Switch>
                    </Router>
                )

            }
        </div>
    );
}

export default WelcomePage;