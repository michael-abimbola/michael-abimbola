import './AuthForm.css';
import { useAuth0 } from '@auth0/auth0-react'
import axios from 'axios';
import React, { useState, useEffect, useContext } from "react";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import AddGraduatignClass from '../UserFunctionalities/Admin/GraduationClass/AddGraduatingClass';
import AddGraduatingYearHooks from '../UserFunctionalities/Admin/GraduationYears/AddGraduatingYearHook';
import HomePage from '../HomePage';
import NavBar from '../NavigationBar/NavBar';
import FormSelector from '../UserFunctionalities/Admin/SignUp/SignupFormSelector';
import UploadImage from '../UserFunctionalities/ImageManagement/UploadImage';
import { useHistory } from "react-router-dom";
import StudentBio from '../UserFunctionalities/Student/StudentBio';
import FarewellMessage from '../UserFunctionalities/Teacher/FarewellMessage';
import UpdateProfile from '../UserFunctionalities/Profiles/UpdateProfile';
import ImageViewControl from '../UserFunctionalities/Admin/ImageManagement/ImageViewControl';
import StudentBioView from '../UserFunctionalities/Viewers/ImageGelleries/StudentBioView';
import WelcomePage from '../UserFunctionalities/Viewers/WelcomePage';
import FarewellMessages from '../UserFunctionalities/Student/FarewellMessges';

export const GuestContext = React.createContext()
const AuthLogin = () => {
    const [Profile, setProfile] = useState({
        username: "",
        pwd: ""
    })
    const [success, setSuccess] = useState(false)
    const [guestLoginSuccess, setGuestLoginSuccess] = useState(false)
    const [error, setError] = useState(false)
    const [count, setCount] = useState(0)
    const [guestCount, setGuestCount] = useState(0)
    const history = useHistory();
    const [clicked, setClicked] = useState(false)

    useEffect(() => {
        if (Profile.username != "" || Profile.pwd != "") {
            axios.post("authenticate", Profile)
                .then((res) => {
                    if (res.status == 200) {
                        let token = res.data.jwt;
                        localStorage.setItem("jwtToken", token)
                        setSuccess(true)
                        setError(false)
                        console.log(res.data.jwt)
                    }
                    else {
                        console.log(res.data)
                        setError(true)
                    }
                })
                .catch((err) => {
                    setError(true)
                    setSuccess(false)
                })
        }
        else {
            setError(false)
            setSuccess(false)
        }
    }, [count])

    useEffect(() => {
        if (clicked == true) {
            axios.post("authenticate/Guest")
                .then((res) => {
                    if (res.status == 200) {
                        let token = res.data.jwt;
                        localStorage.setItem("jwtToken", token)
                        setGuestLoginSuccess(true)
                        setError(false)
                        console.log(res.data.jwt)
                    }
                    else {
                        console.log(res.data)
                        setError(true)
                    }
                })
                .catch((err) => {
                    setError(true)
                    setSuccess(false)
                })
        }

    }, [guestCount])

    const handleSubmit = ((e) => {
        setCount(count + 1)
        e.preventDefault()
        history.push("/homepage")

    })

    const guestLoginSubmit = ((e) => {
        setGuestCount(guestCount + 1)
        setClicked(true)
        e.preventDefault()
        history.push("/welcomepage")
    })
    return (
        <div>
            {!success && !guestLoginSuccess &&
                <div class="container" id="authLoginContainer">
                    <div className="form-container sign-in-container">
                        <form onSubmit={handleSubmit}>
                            <h1>School Accounts Sign in</h1>
                            <input
                                type="text"
                                value={Profile.username}
                                onChange={(e) => setProfile({ ...Profile, username: e.target.value })}
                                placeholder="username......." />
                            <input
                                type="password"
                                value={Profile.pwd}
                                onChange={(e) => setProfile({ ...Profile, pwd: e.target.value })}
                                placeholder="password........." />
                            <button type="submit">Log in</button>
                            <div class="guestLogin">
                                <div class="guestBtnContainer">
                                    <button class="guestBtn" onClick={guestLoginSubmit}>Viewer Login</button>
                                </div>
                                <div class="guestLogoCOntainer">
                                    <img src={process.env.PUBLIC_URL + "./guest.png"} width="60%" height="100%" alt="guestLogo" class="guestLogo" />
                                </div>
                            </div>
                            {error && <h4 id="loginError">Login failed, please ensure your credentials are accurate!</h4>}
                        </form>

                    </div>

                    <div class="overlay-container">
                        <div class="overlay">
                            <div class="overlay-panel overlay-right">
                                <h1>Welcome Back to MCSS Login Page!</h1>
                                <p>Please login in with your personal info</p>
                            </div>
                        </div>
                    </div>
                </div>
            }

            {guestLoginSuccess && (
                <WelcomePage />
            )
            }


            {success && (
                <Router>
                    <NavBar />
                    <Switch>
                        <Route exact path="/homepage">
                            <HomePage />
                        </Route>
                        <Route exact path="/signup">
                            <FormSelector />
                        </Route>
                        <Route exact path="/fareWellMessagesView">
                            <FarewellMessages />
                        </Route>
                        <Route exact path="/imageUpload">
                            <UploadImage />
                        </Route>
                        <Route exact path="/graduationYear">
                            <AddGraduatingYearHooks />
                        </Route>
                        <Route exact path="/graduationClass">
                            <AddGraduatignClass />
                        </Route>
                        <Route exact path="/studentBio">
                            <StudentBio/>
                        </Route>
                        <Route exact path="/farewellMsgAddition">
                            <FarewellMessage/>
                        </Route>
                        <Route exact path="/updateProfile">
                            <UpdateProfile/>
                        </Route>
                        <Route exact path="/permitImages">
                            <ImageViewControl/>
                        </Route>
                        <Route exact path="/studentBioView">
                            <StudentBioView/>
                        </Route>
                    </Switch>
                </Router>
            )}
        </div>
    );
}

export default AuthLogin;