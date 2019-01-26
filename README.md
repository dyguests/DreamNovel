# DreamNovel

## Reference

PageView

[lTBeL/novel](https://github.com/lTBeL/novel)

## TODO

- [ ] 之后为一堆异步初始化的东西加上回调，combineLast

- [ ] dao : rx -> liveData

- [ ] TextProvider 的点击区域，默认文字

## Notice

1. apply plugin: 'kotlin-kapt'

    这里要使用`kotlin-kapt`，直接用自带kapt的话ARouter注解生成不了。
    之后要换成kapt的话需要重新测一下Arouter能用不（clean后）

## Structure

main module

business module

base module

tools module

## 字典

biz  business 业务
wdt widget 控件
tl tool 工具

Paragrafo 段落

## Resources

1. [alibaba/ARouter](https://github.com/alibaba/ARouter)

2. [MultipleItemRvAdapter](https://blog.csdn.net/Chay_Chan/article/details/79658655)