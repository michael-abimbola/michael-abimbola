import { Component } from "react";
import axios from "axios"

class Signup extends Component {
    constructor(props){
        super(props)
        this.state ={
            profileUsrID: "",
            profileName: "",
            profilePwd: "",
            graduatingYear: {profileGraduatingYear: ""},
            graduatingClass: {profileGraduatingClass: ""}
        }
    }

    changeHandler = (e) => {
        this.setState({
        [e.target.name]: e.target.value
        })
    }

    changeHanlderForGradYear = (e) => {
        this.setState({
            graduatingYear: e.target.value
        })
    }

    changeHanlderForGradClass = (e) => {
        this.setState({
            graduatingClass: e.target.value
        })
    }

    submitHandler = (e) => {
        e.preventDefault();
        console.log(this.state)
        axios.post("http://localhost:8080/profile/student/", this.state)
        .then(response => {
            <h2>{this.response}</h2>

        })
        .catch(error => {
            console.log(error)
        })
    }

    render() { 
        const {profileUsrID, profileName, profilePwd, graduatingYear, graduatingClass} = this.state
        return ( 
            <div>
                <form onSubmit = {this.submitHandler}>
                    <div>
                        <input type = "text" 
                        name = "profileUsrID" 
                        value = {profileUsrID}
                        onChange = {this.changeHandler}/>
                    </div>
                    <div>
                        <input type = "text" 
                        name = "profileName" 
                        value = {profileName}
                        onChange = {this.changeHandler}/>
                    </div>
                    <div>
                        <input type = "text" 
                        name = "profilePwd" 
                        value = {profilePwd}
                        onChange = {this.changeHandler}/>
                    </div>
                    <div>
                        <input type = "text" 
                        name = "profileGraduatingYear" 
                        value = {graduatingYear}
                        onChange = {this.changeHanlderForGradYear}/>
                    </div>
                    <div>
                        <input type = "text" 
                        name = "profileGraduatingClass" 
                        value = {graduatingClass}
                        onChange = {this.changeHanlderForGradClass}/>
                    </div>
                    <button type = "submit">Sign up</button>
                </form>
            </div>
        )
    }
}
 
export default Signup