const express = require("express");
const {
  getAllStudent,
  getStudentByClassroom,
  getStudentBySubject,
  getAllMockData,
  getStudentByName,
  deleteStudentById,
} = require("../controllers/studentControllers");

const router = express.Router();

router.get("/", getAllStudent);
router.get("/Mock", getAllMockData);
router.get("/getStudentByName", getStudentByName);
router.get("/getStudentByClassroom/:classroom_name", getStudentByClassroom);
router.get("/getStudentBySubject/:subject_name", getStudentBySubject);
router.get("/getStudentBySubjectId/:subject_id", getStudentBySubject);

router.delete("/deleteStudentById/:student_id", deleteStudentById);

const StudentRoute = router;
module.exports = StudentRoute;
