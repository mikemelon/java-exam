package cn.lynu.lyq.java_exam.actions;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.ExamPhase;
import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Student;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;
import cn.lynu.lyq.java_exam.misc.CustomColorBarRenderer;

@Component("scoresBarChart")
@Scope("prototype")
public class ScoresBarChartAction extends ActionSupport {
	private static final long serialVersionUID = 2217462899746164125L;
	
	private JFreeChart chart;
	@Resource
	private StudentDao studentDao;
	@Resource
	private ExamDao examDao;
	@Resource
	private StudentExamScoreDao studentExamScoreDao;

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	@Override
	public String execute() throws Exception {
//		double[][] data = new double[][] { { 90 , 80 , 77 , 85 } };
//		String[] rowKeys = {""};
//		String[] columnKeys = { "张三", "李四", "王二", "马六" };
		
		ActionContext ctx =ActionContext.getContext();
		String classSearch=ctx.getParameters().get("classSearch").getValue();
		String examNameSearch=ctx.getParameters().get("examNameSearch").getValue();
		System.out.println("classSearch=["+classSearch+"]");
		System.out.println("examNameSearch=["+examNameSearch+"]");
		
//		Student theStudent =(Student)ctx.getSession().get("USER_INFO");
//		List<Student> stuList = studentDao.findByGrade(theStudent.getGrade());
		List<String> examNameList = examDao.findAllDistinctExamName();
		if(!examNameSearch.equals("")){
			examNameSearch=examNameList.get(Integer.parseInt(examNameSearch)-1);
		}else{
			examNameSearch=examNameList.get(0);
		}
		List<StudentExamScore> list1 = studentExamScoreDao.findByClassIdAndExamNameAndExamPhase(classSearch,examNameSearch,
				ExamPhase.FINAL_SCORED.getChineseName());
		List<String> stuNameList = new ArrayList<>();
		List<Double> scoreList = new ArrayList<>();
		
		for(StudentExamScore examScore:list1){
			scoreList.add((double)examScore.getScore());
			Student stu = studentDao.findById(examScore.getStudent().getId());
			stuNameList.add(stu.getName());
		}
		
		String[] stuNameArray = (String[])stuNameList.toArray(new String[stuNameList.size()]);
		Double[] integerScoreArray = (Double[])scoreList.toArray(new Double[scoreList.size()]);
		double[] scoreArray = ArrayUtils.toPrimitive(integerScoreArray);
		
		//排序
		int[] index = new int[scoreArray.length];//原始下标数组
		for(int i=0; i<scoreArray.length; i++){
			index[i] = i;
		}
		sortWithIndex(scoreArray,index);
		//根据分数排序，重新排列姓名数组
		String[] stuNameArraySorted = new String[stuNameArray.length];
		for(int i=0; i<stuNameArray.length; i++){
			stuNameArraySorted[i] = stuNameArray[index[i]];
		}
		
		double[][] data = new double[][] { scoreArray };
		String[] rowKeys = {""};
		String[] columnKeys = stuNameArraySorted;
		System.out.println(">>>>>>>>>>学生名列表："+Arrays.toString(stuNameArraySorted));
		System.out.println(">>>>>>>>>>学生名length："+stuNameArraySorted.length);
		System.out.println(">>>>>>>>>>分数列表："+Arrays.toString(scoreArray));
		System.out.println(">>>>>>>>>>分数length"+scoreArray.length);
		
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);

		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("SimHei", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		
		standardChartTheme.setSmallFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);

		chart = ChartFactory.createBarChart3D("成绩分布图", "姓名", "成绩", dataset, PlotOrientation.HORIZONTAL, false,
				false, false);
		CategoryPlot plot = chart.getCategoryPlot();// 获得图表区域对象
		CustomColorBarRenderer renderer = new CustomColorBarRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE4, TextAnchor.BASELINE_RIGHT));
		renderer.setItemLabelAnchorOffset(25D);
		renderer.setBaseItemLabelsVisible(true);  
		plot.setRenderer(renderer);
		return SUCCESS;
	}
	
	/*
	 * 冒泡排序 并返回 排序对应的原始下标
	 */
	public void sortWithIndex(double[] array, int[] index){
//		index = new int[array.length];//原始下标数组
//		for(int i=0; i<array.length; i++){
//			index[i] = i;
//		}
		for(int i=0; i<array.length; i++){//冒泡轮次i
			for(int j=0; j<array.length-i-1; j++){//参与比较的数组下标j
				if(array[j]<array[j+1]){
					double tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
					
					int tmp2 = index[j];
					index[j] = index[j+1];
					index[j+1] = tmp2;
				}
			}
		}
	}
	
}
