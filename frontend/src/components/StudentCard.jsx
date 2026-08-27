function StudentCard({ student, onDelete }) {
    return (
        <div>
            <h2>{student.name}</h2>
            <p>Age: {student.age}</p>
            <p>Percentage: {student.percentage}</p>

            <button onClick = {() => onDelete(student.id)}>
                Delete
            </button>

        </div>
    );
}

export default StudentCard;