package org.example.barrage;

import org.example.ConsoleApplication;
import org.example.core.bgevnet.BarrageEvent;
import org.example.core.bgevnet.bghot.BarragePopularRangePlugin;
import org.example.core.bgevnet.bghot.PopularRange;
import org.example.core.bgevnet.bgscore.BarragePoint;
import org.example.core.bgevnet.bgscore.BarrageScoreCurvePlugin;
import org.example.util.TimeUtil;
import org.example.util.VideoUtil;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.awt.*;
import java.util.List;

/**
 * @author Genius
 * @date 2023/09/14 01:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsoleApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BarrageCurveTest {

    @Resource
    BarrageScoreCurvePlugin plugin;

    @Resource
    BarragePopularRangePlugin popularRangePlugin;

    @Before
    public void setUp() {
        System.setProperty("java.awt.headless", "false");
    }

    @Test
    public void testCurve(){
        long l = System.currentTimeMillis();
        List<BarragePoint> list = plugin.generateCurve(new BarrageEvent("bilibili", "online", "Asaki大人", "2023-09-19 22_00_06"));
        System.out.println(System.currentTimeMillis()-l);
        LineChartDemo demo = new LineChartDemo("Asaki大人",list);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        List<PopularRange> range = popularRangePlugin.findRange(list);
        System.out.println(range);
        int i = 0;
        for (PopularRange popularRange : range) {
            long timeNaos = TimeUtil.getTimeNaos("2023-09-19 22:00:06");
            long videoStartTime = popularRange.getStartTime()-timeNaos;
            videoStartTime = videoStartTime<0?0:videoStartTime;
            long videoEndTime = popularRange.getEndTime()-timeNaos;
            System.out.println(videoStartTime+" "+videoEndTime);
            VideoUtil.cutVideoByFFMpeg("E:\\Project\\ChopperBot\\chopperbot-test\\config\\LiveRecord\\online\\bilibili\\Asaki大人_2023-09-19 22_00_06.flv",
                    "E:\\Project\\ChopperBot\\chopperbot-test\\config\\LiveRecord\\online\\bilibili\\Asaki大人_2023-09-19 22_00_06"+i+".flv",
                    videoStartTime/1000,videoEndTime/1000);
            i++;
        }
        while (true){

        }
    }

    class LineChartDemo extends ApplicationFrame {
        private java.util.List<BarragePoint> list;

        private int sum = 0;
        public LineChartDemo(String title, java.util.List<BarragePoint> list) {
            super(title);
            this.list = list;
            JFreeChart chart = createChart(createDataset());
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
            setContentPane(chartPanel);
        }

        private XYSeriesCollection createDataset() {
            XYSeries series = new XYSeries("score");

            // 生成一些随机数据
            int size = this.list.size();
            for(int i=0;i<size;i++){
                sum+=list.get(i).getPointScore();
                series.add(i,list.get(i).getPointScore());
            }

            XYSeriesCollection dataset = new XYSeriesCollection(series);
            return dataset;
        }

        private JFreeChart createChart(XYSeriesCollection dataset) {
            JFreeChart chart = ChartFactory.createXYLineChart(
                    "BarrageCurve", // 图表标题
                    "Time Point",            // X 轴标题
                    "Barrage Score",            // Y 轴标题
                    dataset,             // 数据集
                    PlotOrientation.VERTICAL, // 图表方向
                    true,                 // 是否显示图例
                    true,                 // 是否生成工具提示
                    false                 // 是否生成 URL 链接
            );

            // 自定义曲线图的样式
            XYPlot plot = chart.getXYPlot();
            XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
            renderer.setSeriesPaint(0, Color.BLUE); // 设置线条颜色
            renderer.setSeriesStroke(0, new BasicStroke(2.0f)); // 设置线条粗细
            plot.setRenderer(renderer);

            // 添加水平线标记
            Marker target = new ValueMarker((double)sum/list.size());
            target.setPaint(Color.RED);
            plot.addRangeMarker(target);

            return chart;
        }
    }
}
