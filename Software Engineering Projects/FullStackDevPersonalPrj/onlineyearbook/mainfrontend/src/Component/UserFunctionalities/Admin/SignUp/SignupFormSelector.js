import './SignForms.css';
// import '.../Login/AuthForm.css';
import $ from "jquery"
import AdminSignUp from "./AdminSignUp";
import StudentSignUp from "./StudentSignUp";
import TeacherSignUp from "./TeacherSignUp";
import YBCMSignUp from "./YBCMSignUp";

const FormSelector = () => {
    $(document).ready(function(){
        $('input[type= "radio"]').click(function(){
            var demovalue = $(this).val();
            $("div.form-container").hide();
            $("#show"+demovalue).show();
        })
    })
    return ( 
        <div class="container" id="container">
            <YBCMSignUp/>
            <TeacherSignUp/>
            <StudentSignUp/>
            <AdminSignUp/>
            <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-right">
                    <h1>Select Sign up form</h1>
                    <label className = "rbcontainer">
                    <input className = "radiobtn" type = "radio" name = "radio" value = "One"/> 
                    <div className="radiodiv"></div>
                    Admin
                    </label>

                    <label className = "rbcontainer">
                    <input className = "radiobtn" type = "radio" name = "radio" value = "Two"/> 
                    <div className="radiodiv"></div>
                    Student
                    </label>

                    <label className = "rbcontainer">
                    <input className = "radiobtn" type = "radio" name = "radio" value = "Three"/> 
                    <div className="radiodiv"></div>
                    Teacher
                    </label>

                    {/* <label className = "rbcontainer">
                    <input className = "radiobtn" type = "radio" name = "radio" value = "Four"/>
                    <div className="radiodiv"></div> 
                    YearBook CM
                    </label> */}
                </div>
            </div>
            </div>
        </div>
    );
}
 
export default FormSelector;