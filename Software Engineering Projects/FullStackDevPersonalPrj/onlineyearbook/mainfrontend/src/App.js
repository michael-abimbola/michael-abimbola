import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import AuthLogin from './Component/Login/AuthLogin';




function App() {
  return (
    <div className="App">
    <Router>
      <Switch>
        <Route path="/">
          <AuthLogin/>
        </Route>
      </Switch>
    </Router>
  </div>
  );
}

export default App;
