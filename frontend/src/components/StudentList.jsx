import StudentCard from "./StudentCard";

function StudentList({ students, onDelete }) {

    return (
        <div>
            {students.map((student) => (
                <StudentCard
                    key={student.id}
                    student={student}
                    onDelete={onDelete}
                />
            ))}           
        </div>
    );
}

export default StudentList;