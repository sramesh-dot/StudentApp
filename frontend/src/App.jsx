import { useState } from 'react'
import './App.css'

import StudentCard from "./components/StudentCard";
import StudentList from "./components/StudentList";

function App() {

    const [students, setStudents] = useState([
        {
            id: 1,
            name: "Ramesh",
            age: 22,
            percentage: 82
        },
        {
            id: 2,
            name: "Arun",
            age: 21,
            percentage: 75
        },
        {
            id: 3,
            name: "Kumar",
            age: 23,
            percentage: 91
        }
    ]);

    const [name, setName] = useState("");
    const [age, setAge] = useState("");
    const [percentage, setPercentage] = useState("");

    function handleSubmit(event) {
        event.preventDefault();
        const newStudent = {
            id: Date.now(),
            name: name,
            age: Number(age),
            percentage: Number(percentage)
        };
        setStudents([...students, newStudent]);
        setName("");
        setAge("");
        setPercentage("");
    }

    function handleDelete(id) {
        setStudents(
            students.filter(student => student.id !== id));
    }

    function handleClearAll() {
        setStudents([]);
    }
    // function addStudent() {
    //     const newStudent = {
    //         id: 4,
    //         name: "Suresh",
    //         age: 22,
    //         percentage: 85
    //     };
    //     setStudents([...students, newStudent]);
    // }

    // function removeStudent() {
    //     setStudents(
    //         students.filter(student =>
    //             student.id !== 3)
    //         );
    // }
    return (
        <div>
            <h1>StudentApp</h1>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Name:</label>
                <input
                    type="text"
                    placeholder='Enter name'
                    value={name}
                    onChange={(event)=>
                        setName(event.target.value)
                    }
                />
                </div>

                <div>
                    <label>Age:</label>
                <input
                    type="text"
                    placeholder='Enter age'
                    value={age}
                    onChange={(event)=>
                        setAge(event.target.value)
                    }
                />
                </div>


                <div>
                    <label>Percentage:</label>
                <input
                    type="text"
                    placeholder='Enter percentage'
                    value={percentage}
                    onChange={(event)=>
                        setPercentage(event.target.value)
                    }
                />
                </div>

                <button type="submit">
                    Add Student
                </button>
                

            </form>

            {/* <button onClick={addStudent}>
                Add Student
            </button> */}
           
            <StudentList
                 students={students}
                 onDelete={handleDelete}
                 onClear={handleClearAll}/>

             {/* <button onClick={removeStudent}>
                Remove Student
            </button> */}
        
        </div>
    );
}


export default App;