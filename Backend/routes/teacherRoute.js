const express = require("express");
const {
  getAllTeachers,
  getTeacherBySubject,
  getAvailableTeacher,
  deleteTeacherById,
} = require("../controllers/teacherController");

const router = express.Router();

router.get("/", getAllTeachers);
router.get("/available", getAvailableTeacher);
router.get("/getTeacherBySubject/:name", getTeacherBySubject);

router.delete("/deleteTeacherById/:teacher_id", deleteTeacherById);

const TeacherRoute = router;
module.exports = TeacherRoute;
