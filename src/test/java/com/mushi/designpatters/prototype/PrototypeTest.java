package com.mushi.designpatters.prototype;

import org.junit.Assert;
import org.junit.Test;

/**
 * @ Author     ：Mushishi
 * @ Desc       ：
 * @ Version    :
 * @ Date       ：2018/10/11 15:13
 */
public class PrototypeTest {

    @Test
    public void test() throws CloneNotSupportedException {
        String internetWorker = "Internet Worker";
        String blockChainWorker = "Block Chain Worker";

        String theNobelPrizeName = "The Nobel Prize";
        String turingAwardName = "Turing Award";
        String academyAwardsName = "Academy Awards";
        String awsAwardName = "AWS Award";


        Resume resume = new Resume("rain");
        resume.setPersonalInfo("Male",27);
        resume.setWorkExperience("2012-2018",internetWorker);
        resume.setPrize(new Prize(turingAwardName));

        Resume resumeCloned = resume.clone();
        Resume resumeDeepClone = resume.deepClone();
        Resume resumeClonedNew = resume.clone();

        resumeCloned.setPersonalInfo("Female",27);
        resumeDeepClone.setWorkExperience("2012-2016",blockChainWorker);


        Prize prize = resumeCloned.getPrize();
        prize.setName(theNobelPrizeName);
        resumeCloned.setPrize(prize);

        Prize prizeDeep = resumeDeepClone.getPrize();
        prizeDeep.setName(academyAwardsName);
        resumeDeepClone.setPrize(prizeDeep);

        resumeClonedNew.setPrize(new Prize(awsAwardName));


        Assert.assertEquals("Male",resume.getSex());
        Assert.assertEquals("Female",resumeCloned.getSex());

        Assert.assertEquals(internetWorker, resume.getWorkExperience().getCompany());
        Assert.assertEquals(blockChainWorker, resumeDeepClone.getWorkExperience().getCompany());

        //原对象受浅拷贝对象引的影响
        Assert.assertEquals(theNobelPrizeName, resume.getPrize().getName());
        //浅拷贝对象
        Assert.assertEquals(theNobelPrizeName, resumeCloned.getPrize().getName());
        //深拷贝对象不会影响原对象的引用
        Assert.assertEquals(academyAwardsName, resumeDeepClone.getPrize().getName());
        //浅拷贝的对象引用是新实例化出来时不会影响原对象
        Assert.assertEquals(awsAwardName, resumeClonedNew.getPrize().getName());


    }


}
