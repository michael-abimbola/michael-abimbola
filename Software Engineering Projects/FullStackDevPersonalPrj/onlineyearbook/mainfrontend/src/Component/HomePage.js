import React, {useContext} from "react"
import {useAuth0} from '@auth0/auth0-react'

const HomePage = () => {
    const {logout, isAuthenticated} = useAuth0()
    // const gradID = useContext(ChosenGradYearContext)
    return ( 
        <div className = "homePage">
            {/* <h1>{gradID}</h1> */}
            <img src = {process.env.PUBLIC_URL + "./test.jpg"} width = "80%" alt = "scndlogo"/>
            {isAuthenticated && (
                <button onClick={() => logout()}>
                    Logout
                </button>)}
        </div>
    )
}
 
export default HomePage;