import { useEffect } from 'react';
import './App.css';

function App() {
  useEffect(() => {
    fetch("http://localhost:8080/")
    .then(res => res.json())
    .then((result) => {
      console.log(result)
    })
    .catch(error => {
      console.log('error')
    })
  }, [])
  return (
    <div className="App">
      
    </div>
  );
}

export default App;
