function StudentCard({ student, onDelete }) {
    return (
        <div>
            <p>{student.id}</p>
            <p>{student.name}</p>
            <p>Age: {student.age}</p>
            {/* <p>Percentage: {student.percentage}</p> */}

            <button onClick = {() => onDelete(student.id)}>
                Delete
            </button>

        </div>
    );
}

export default StudentCard;