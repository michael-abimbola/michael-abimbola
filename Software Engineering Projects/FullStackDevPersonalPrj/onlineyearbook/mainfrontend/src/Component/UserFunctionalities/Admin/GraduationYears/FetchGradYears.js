import axios from "axios"
import React, { useReducer,  useEffect } from "react"
// import authHeader from "../../auth-header"

const initialState = {
    loading: true,
    error: "",
    gradYears: []
}

const user = localStorage.getItem("jwtToken")

const reducer = (state, action) => {
    switch (action.type) {
        case "FETCH_SUCCESS":
            return {
                loading: false,
                error: "",
                gradYears: action.payload
            }
        case "FETCH_ERROR":
            return {
                loading: false,
                error: "Something went wrong trying to fethc the required data, please refresh",
                gradYears: []
            }
        default:
            return state
    }
}

const FetchGradYears = () => {
    const [state, dispatch] = useReducer(reducer, initialState)
    useEffect(() => {
        axios.get("graduatingyear",{
            headers:{
                "Authorization": `Bearer ${user}`
            }
        })
            .then((res) => {
                dispatch({
                    type: "FETCH_SUCCESS",
                    payload: res.data
                })
            })
            .catch((err) => {
                dispatch({
                    type: "FETCH_ERROR"
                })
                console.log(err)
            })
    })

    return state.gradYears.map((item) => {
        return ( 
            <div key = {item.graduatingYear} className = "gradYears">
                 {state.loading ? <p className = "fetchLoad">Loading....</p> : <p>Graduating Year: {item.graduatingYear} <br></br></p>}
                 {state.error ?<p className = "fetchError">Loading</p> : null}
            </div>
        );
    })

}

export default FetchGradYears;