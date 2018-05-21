package cn.lynu.lyq.java_exam.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lynu.lyq.java_exam.common.ExamPhase;
import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Exam;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;

@Component("studentExamScoreDao")
@Transactional

public class StudentExamScoreDaoImpl implements StudentExamScoreDao {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private StudentDao studentDao;
	@Resource
	private GradeDao gradeDao;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByStudent(Student s) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where student=?");
		q.setParameter(0, s);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByExamPhase(String examPhase) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where examPhase=? order by score desc");
		q.setParameter(0, examPhase);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByClassIdAndExamNameAndExamPhase(String classId, String examName,
			String examPhase) {
		StringBuilder sb = new StringBuilder();
		sb.append("from StudentExamScore ses where ");
		
		classId = classId.trim();
		int nPos = 0;
		String[] ids = null;
		if(!classId.equals("")){
			ids = classId.split(",");
			sb.append("(");
			for(int i=0; i<ids.length; i++){
				sb.append("ses.student.grade.id=?");
				if(i<ids.length-1) sb.append(" or ");
				nPos++;
			}
			sb.append(") and ");
		}
		sb.append("ses.exam.name like ? and ses.examPhase=?  order by score desc");
		Query q=sessionFactory.getCurrentSession().createQuery(sb.toString());
		for(int i=0; i<nPos; i++) q.setInteger(i, Integer.parseInt(ids[i].trim()));
		q.setParameter(nPos++, examName+"%");
		q.setParameter(nPos, examPhase);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByStudentAndExamPhase(Student s, String examPhase) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where student=? and examPhase=? order by examEndTime desc");
		q.setParameter(0, s);
		q.setParameter(1, examPhase);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findByStudentAndExam(Student s, Exam e) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore where student=? and exam=?");
		q.setParameter(0, s);
		q.setParameter(1, e);
		return q.list();
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public StudentExamScore findById(int id) {
		StudentExamScore ses = sessionFactory.getCurrentSession().get(StudentExamScore.class, id);
		return ses;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentExamScore> findAll() {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore");
		return q.list();
	}
	
	@Override
	public void save(StudentExamScore ses){
		sessionFactory.getCurrentSession().save(ses);
	}
	
	@Override
	public void update(StudentExamScore ses){
		sessionFactory.getCurrentSession().update(ses);
	}
	
	@Override
	public void delete(StudentExamScore ses){
		sessionFactory.getCurrentSession().delete(ses);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Student> getAbsentStudentsForExamName(String classId, String examName) {
		Query q=sessionFactory.getCurrentSession().createQuery("from StudentExamScore ses where ses.exam.name like ? "
				+ "and ses.student.grade.id=?"
				+ "and ses.examPhase=?");
		int gradeId = Integer.parseInt(classId.trim());
		q.setString(0, examName+"%");
		q.setInteger(1,gradeId);
		q.setString(2, ExamPhase.FINAL_SCORED.getChineseName());
		List<StudentExamScore> sesList = q.list();
		List<Student> stuList = studentDao.findByGrade(gradeDao.findById(gradeId));
		List<Student> absentStuList = new ArrayList<>();
		for(Student stu:stuList){
			boolean stuAbsent = true;
			for(StudentExamScore ses:sesList){
				if(ses.getStudent().getId()==stu.getId()){
					stuAbsent = false;
					break;
				}
			}
			if(stuAbsent) absentStuList.add(stu);
		}
		return absentStuList;
	}

}
