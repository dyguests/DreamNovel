package com.fanhl.dreamnovel.ui.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fanhl.dreamnovel.R
import com.fanhl.widget.page.Chapter
import kotlinx.android.synthetic.main.activity_page_test.*

val text = """
    前端数据处理能力的提升
目前前端的框架可单独打包前端工程，运行于nodejs环境中，由于nodejs基于v8引擎打造，计算能力与数据处理能力极大提升，使得一些数据处理的业务可由后端转入前端，如带有搜索栏的表单，多种排序规则的list，，提高服务质量，提升前端可维护性。
减少对开发人员的前端要求（前后分工明确）
由于目前校聘人员较多，前端基础薄弱，甚至不曾接触过前端技术，培训难度较高，甚至在重后端轻前端的技术大氛围下以及前端的‘麻烦’，使得大家对前端较为排斥。
技术的发展，前后端技术的差异性也日异明显，如果仍然以传统web开发模式来实现，短时间也不能确保公司员工都能精通全栈开发，进行前后端分离，后端更注重的是服务提供，不需要考虑前端的终端情况，至于布局、实现数据渲染、展示等工作交由前端完成，分工更明确，减少了前后端的耦合，降低了合作难度。
前后端交互的为json数据而非页面，交互更加快捷方便
前后端技术发展太快，没有那么多人能兼顾，前端与后端的进步，使得各方在自己的领域深耕，加快了技术进步，提高了用户的体验，
前端es5，es6， es7都出来了，后端目前公司仍使用java7,8而目前java已经出现了11版本，在9之后的新特性使得java在数据处理上更加快。而新的语法糖也使得我们开发人员工作效率更高，写出的东西更简洁可读。。
前后端的技术栈也越来越广，从ssh到ssm再到现在的springboot整合，从简单的mybatis加mysql到现在的redis,es,kettle,zookepper，dubbo，springcloud。
判断数据量，大的后端处理，小的前端处理
未来技术基础
目前已在某些大公司，开始使用前端微服务架构，而前端微服务架构的基础是spa模式，技术是不断更新的，一步落下，有可能就得步步落下，为了长远的技术考虑，我们也需要进行技术更迭。
从互联网服务的角度来看
前端多平台思考--（vue一站式开发web安卓ios微信小程序）
https://www.jianshu.com/p/a7bdb2b08e1e

作者：想转行的入门级程序员
链接：https://www.jianshu.com/p/dfd666fbb3f8
來源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
""".trimIndent()

class PageTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_test)

        val chapter = Chapter(content = text)

        chapter_view.post {
            chapter_view.chapter = chapter
        }
    }
}
