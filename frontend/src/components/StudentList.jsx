import StudentCard from "./StudentCard";

function StudentList({ students, onDelete, onClear }) {

    return (
        <div>
            {students.map((student) => (
                <StudentCard
                    key={student.id}
                    student={student}
                    onDelete={onDelete}
                />
            ))}
            <button onClick = {() => onClear()}>
                Clear All Students
            </button>
           
        </div>
    );
}

export default StudentList;