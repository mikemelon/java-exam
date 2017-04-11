package cn.lynu.lyq.java_exam.test;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class BarChartTest {

	public static void main(String[] args) throws IOException {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("B（张三,李四）", new Double(20));
		dataset.setValue("A（牛八,牛九）", new Double(20));
		dataset.setValue("C（胡十,胡九,胡瓜,胡弄）", new Double(40));
		dataset.setValue("D（邓三）", new Double(10));

		JFreeChart chart = ChartFactory.createPieChart("本题正确答案：C", // chart title
				dataset, // data
				false, // include legend
				false, false);
		setChartProperties(chart);
		ChartUtilities.saveChartAsPNG(new File("d:\\testPie.png"), chart, 600, 400);
	}
	
	 /**       
     * 设置Chart属性，可解决乱码问题       
     * @param chart 统计图标       
     */
    private static void setChartProperties(JFreeChart chart){       
                              
        //三个部分设置字体的方法分别如下:         
        TextTitle textTitle = chart.getTitle();         
        textTitle.setFont(new Font("SimHei", Font.PLAIN, 20));         
        LegendTitle legend = chart.getLegend();  
        if (legend != null) {         
            legend.setItemFont(new Font("Microsoft YaHei", Font.PLAIN, 15));         
        }         
        PiePlot pie = (PiePlot) chart.getPlot();         
        pie.setLabelFont(new Font("Microsoft YaHei", Font.PLAIN, 15));         
        pie.setNoDataMessage("No data available");         
        //设置PieChart是否显示为圆形    
        pie.setCircular(true);    
        // 间距    
        pie.setLabelGap(0.05D);       
    }     

}
