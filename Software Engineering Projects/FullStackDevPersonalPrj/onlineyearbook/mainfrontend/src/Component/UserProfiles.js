import React, { useReducer, useEffect } from "react"
import axios from "axios"

const initialState = {
    loading: true,
    error: "",
    usrProfiles: []
}

const reducer = (state, action) => {
    switch (action.type) {
        case "FETCH_SUCCESS":
            return {
                loading: false,
                error: "",
                usrProfiles: action.payload
            }
        case "FETCH_ERROR":
            return {
                loading: false,
                usrProfiles: [],
                error: "Something went wrong"
            }
        default:
            return state
    }
}


const UserProfiles = () => {
    const [state, dispatch] = useReducer(reducer, initialState)

    useEffect(() => {
        axios.get(`profile`)
            .then((res) => {
                dispatch({ type: "FETCH_SUCCESS", payload: res.data })
                console.log(res)
            })
            .catch((err) => {
                dispatch({ type: "FETCH_ERROR" })
                console.log(err)
            })
    }, [])

    return state.usrProfiles.map(item => {
        return (
            <div className="profilesViewContainer" key={item.profileUserID}>
                {state.loading ? <p className = "fetchLoad">Loading.....</p> : <p>UserID: {item.profileUserID} <br></br>
                Profile Name: {item.profileName}
                {item.graduatingYear && <p>Student Graduation Year:  {item.graduatingYear.graduatingYear}</p>}</p>}
                {state.error ?<p className = "fetchError">Loading</p> : null}
            </div>
        )
    })

}

export default UserProfiles;