## I.Description
This file describes how to collabrate on this project 

## II.Basic Concept 
需要先了解基本概念 请参考 git 教程
包括

- 远程仓库
- 本地仓库
- 本地仓库的工作区和缓存区
- 主分支
- 从分支

基本操作
- clone
- add 
- commit 
- push
- pull
- merge
- branch



## III. Guideline

我们远程仓库只存放master分支 ，然后本地仓库克隆这个master分支 ，本地再分支出一个dev 分支 作为每个人的开发分支 。当需要添加新功能时， 从dev分支上分支出一个 Feature 分支 在这个feature分支上进行开发 。开发结束之后合并到自己的dev 分支上 代表这个功能开发完成。 此时不能着急push ， 需要先更新自己的master分支 因为master分支可能已经更改了。 更新之后 再将本地dev分支上的内容merge到master分支 最后pushmaster分支



命令版本

1. 我们远程仓库只存放master分支

2. 每个人本地仓库克隆这个master分支 

   ```bash
   1. git clone https://github.com/neusoft-training-Crossboarder-Shop/shop.git
   2. 切换到这个目录里
   	cd shop
   ```

   

3. 本地再创建一个dev分支 作为每个人的开发分支

   ```bash
   1. git branch dev   //创建分支
   2. git checkout dev //切换到这个分支 
   tips: 可以使用组合命令 git checkout -b dev  创建并切换到这个分支
   ```

   (如果你不是添加一个大的新功能你可以不用创建feature分支  在dev分支上进行操作即可。)

   上面的是你第一次该执行的，下面的步骤是可循环执行的

4.  当你需要为本项目添加新功能时，请创建一个分支，在这个feature分支上进行开发 。

   ```bash
   1.git checkout -b feature-name
   
   若干次本地提交：
   git add .   
   git commit -m "[2] feature name : what are you doing for this commit "
   
   组合命令 git commit -a -m " decription "
   ```

   

5. 开发结束之后合并到自己的dev 分支，代表这个功能开发完成。 

``` bash
1. git checkout dev  切换到dev分支
2. git merge feature-name  合并 feature-name 分支  之后你可以删除这个功能分支
```

6. 此时不能着急push ， 需要先更新自己的master分支 并且在dev分支上将新的内容与新的master合并 ， 因为master分支，在你开发的时候 可能已经被push过了。 

```bas
1.git checkout master 切换到主分支
2.git pull 更新主分支，现在主分支的内容和远程保持了一致
在从分支上进行合并
3.git checkout dev  
4.git merge master 
```

7. 更新之后 再将本地dev分支上的内容merge到master分支 

```bash
git checkout master 
git merge dev 
```



8. 最后pushmaster分支

```bash 
git push 
```

9.BranchName

_7_1_MVO_MODULE

_7_1_BVO_MODULE