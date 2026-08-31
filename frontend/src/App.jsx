import { useState, useEffect } from 'react'
import './App.css'

import StudentCard from "./components/StudentCard";
import StudentList from "./components/StudentList";

function App() {

    // const [students, setStudents] = useState([
    //     {
    //         id: 1,
    //         name: "Ramesh",
    //         age: 22,
    //         percentage: 82
    //     },
    //     {
    //         id: 2,
    //         name: "Arun",
    //         age: 21,
    //         percentage: 75
    //     },
    //     {
    //         id: 3,
    //         name: "Kumar",
    //         age: 23,
    //         percentage: 91
    //     }
    // ]);

    const [name, setName] = useState("");
    const [age, setAge] = useState("");
    const [id, setId] = useState("");

    function handleSubmit(event) {
        event.preventDefault();
        const newStudent = {
            // id: Date.now(),
            name: name,
            age: Number(age),
            id: Number(id)
        };
        // setStudents([...students, newStudent]);
        handleAddStudent(newStudent);
        setName("");
        setAge("");
        setId("");
    }

    // function handleDelete(id) {
    //     setStudents(
    //         students.filter(student => student.id !== id));
    // }

    // function handleClearAll() {
    //     setStudents([]);
    // }
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
    // const [count, setCount] = useState(0);

    // useEffect(() =>{
    //     console.log("Count changed:", count);   
    // })

//Get method
    const [students,setStudents] = useState([]);
    useEffect(() => {

    fetch("http://localhost:8080/students", {
        method: "GET",
        headers: {
            "Authorization": "Basic " + btoa("admin:admin123"),
            "Content-Type": "application/json"

        }
    })
        .then(response => {

            if (!response.ok) {
                throw new Error("Failed to fetch students");
            }

            return response.json();
        })
        .then(data => {
            setStudents(data);
        })
        .catch(error => {
            console.error(error);
        });

}, []);

//Post method
const handleAddStudent = async (student) => {

    const response = await fetch(
        "http://localhost:8080/students",
        {
            method: "POST",
            headers: {
                "Authorization": "Basic " + btoa("admin:admin123"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify(student)
        }
    );

    const savedStudent = await response.json();

    setStudents(previousStudents => [
        ...previousStudents,
        savedStudent
    ]);
};

//Delete method
const handleDelete = async (id) => {
    await fetch(`http://localhost:8080/students/${id}`, {
        method: "DELETE",
        headers: {
            "Authorization": "Basic " + btoa("admin:admin123"),
        }
    });

    setStudents(prev => prev.filter(student => student.id !== id));
};

//Put method
const handleUpdate = async () => {

    const student = {
        id: Number(id),
        name: name,
        age: Number(age)
    };

    const response = await fetch(
        `http://localhost:8080/students/${id}`,
        {
            method: "PUT",
            headers: {
                "Authorization": "Basic " + btoa("admin:admin123"),
                "Content-Type": "application/json"
            },
            body: JSON.stringify(student)
        }
    );

    if (!response.ok) {
        throw new Error("Failed to update student");
    }

    const updatedStudent = await response.json();

    setStudents(prev =>
        prev.map(s =>
            s.id === updatedStudent.id
                ? updatedStudent
                : s
        )
    );

    setName("");
    setAge("");
    setId("");
};
    return (
        <div>
            <h1>StudentApp</h1>
            {/* <h1>{count}</h1>
            <button onClick={() => setCount(count+1)}>
                Increase
            </button> */}

            <form onSubmit={handleSubmit}>
                
                <div>
                    <label>ID:</label>
                <input
                    type="text"
                    placeholder='Enter ID'
                    value={id}
                    onChange={(event)=>
                        setId(event.target.value)
                    }
                />
                </div>

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

                <button type="submit">
                    Add Student
                </button>

                <button 
                    type="button"
                    onClick={handleUpdate}>
                    Update
                </button>
                

            </form>

            {/* <button onClick={addStudent}>
                Add Student
            </button> */}
           
            <StudentList
                 students={students}
                 onDelete={handleDelete}
                 />

             {/* <button onClick={removeStudent}>
                Remove Student
            </button> */}
        
        </div>
    );
}


export default App;