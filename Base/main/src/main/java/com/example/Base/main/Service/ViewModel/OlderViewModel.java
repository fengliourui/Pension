package com.example.Base.main.Service.ViewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.Base.main.Service.Data.Activity.ActivityOne;
import com.example.Base.main.Service.Data.Activity.ActivityUsers;
import com.example.Base.main.Service.Data.Activity.ActivtyUser;
import com.example.Base.main.Service.Data.Activity.Activtyall;
import com.example.Base.main.Service.Data.Activity.Postactivity;
import com.example.Base.main.Service.Data.Activity.signUpActivityVo;
import com.example.Base.main.Service.Data.Health.ShowData;
import com.example.Base.main.Service.Data.Parameter.BinOlder;
import com.example.Base.main.Service.Data.Parameter.Comm;
import com.example.Base.main.Service.Data.Parameter.Five;
import com.example.Base.main.Service.Data.Parameter.Mss;
import com.example.Base.main.Service.Data.Parameter.Three;
import com.example.Base.main.Service.Data.Parameter.Two;
import com.example.Base.main.Service.Data.Parameter.Two1;
import com.example.Base.main.Service.Data.add.Binddat;
import com.example.Base.main.Service.Data.add.Commentlikes;
import com.example.Base.main.Service.Data.add.Deletecommentld;
import com.example.Base.main.Service.Data.add.GetCommentld;
import com.example.Base.main.Service.Data.add.Numdeals;
import com.example.Base.main.Service.Data.add.OlderBind;
import com.example.Base.main.Service.Data.add.VideoMessage;
import com.example.Base.main.Service.Data.add.nameAndIdentifyId;
import com.example.Base.main.Service.Data.add.publishCommentVo;
import com.example.Base.main.Service.Data.nusion.Nurse;
import com.example.Base.main.Service.Data.older.Avatar;
import com.example.Base.main.Service.Data.older.Message;
import com.example.Base.main.Service.Data.older.Name;
import com.example.Base.main.Service.Data.older.Older;
import com.example.Base.main.Service.Data.older.Postavaterphoto;
import com.example.Base.main.Service.Data.older.userAvatarVo;
import com.example.Base.main.Service.Data.photo.Imageview;
import com.example.Base.main.Service.Repository;

import java.io.File;



public class OlderViewModel extends ViewModel {
    public MutableLiveData<String> locationLiveData = new MutableLiveData<>();//老人的基本信息
    public MutableLiveData<String> locationLiveData1 = new MutableLiveData<>();//老人的头像
    public MutableLiveData<Two1> locationLiveData2 = new MutableLiveData<>();//老人更换头像，提交头像
    public MutableLiveData<String> locationLiveData3 = new MutableLiveData<>();//获取所有活动展示
    public MutableLiveData<Two> locationLiveData4 = new MutableLiveData<>();//由一个id得到一个信息的详细信息
    public MutableLiveData<Two> locationLiveData5 = new MutableLiveData<>();//由一个id得报名活动
    public MutableLiveData<String> locationLiveData6 = new MutableLiveData<>();//得到老人收藏视频
    public MutableLiveData<Three> locationLiveData7 = new MutableLiveData<>();//删除评论
    public MutableLiveData<Three> locationLiveData8 = new MutableLiveData<>();//获取评论
    public MutableLiveData<Two> locationLiveData9 = new MutableLiveData<>();//得根据id获取评论点赞数
    public MutableLiveData<Two> locationLiveData10 = new MutableLiveData<>();//得根据id获取评论点赞数
    public MutableLiveData<Three> locationLiveData11 = new MutableLiveData<>();//得根据id获取评论点赞数

    public MutableLiveData<Two> locationLiveData12 = new MutableLiveData<>();//获取老人参加的活动
    public MutableLiveData<Five> locationLiveData13 = new MutableLiveData<>();//评论的点赞处理
    public MutableLiveData<Comm> locationLiveData14 = new MutableLiveData<>();//评论的点赞处理
    public MutableLiveData<String> locationLiveData15 = new MutableLiveData<>();//获取用户信息
    public MutableLiveData<Mss> locationLiveData16 = new MutableLiveData<>();//修改昵称
    public MutableLiveData<String> locationLiveData17 = new MutableLiveData<>();//获取所有的已绑定的信息
    public MutableLiveData<BinOlder> locationLiveData18 = new MutableLiveData<>();//绑定新的老人
    public MutableLiveData<Two> locationLiveData19 = new MutableLiveData<>();
    public MutableLiveData<Two> locationLiveData20 = new MutableLiveData<>();

    public LiveData<Older> OlderLiveData = Transformations.switchMap(locationLiveData, auth -> {//老人的基本信息
        return  Repository.getOlderLiveData(auth);
    });

    public LiveData<Avatar> avatarLiveData = Transformations.switchMap(locationLiveData1, auth -> {//老人的头像
        return  Repository.getavataravatar(auth);
    });

    public LiveData<Postavaterphoto>PoatPhotoLiveData = Transformations.switchMap(locationLiveData2, two -> {//老人更换头像，提交头像
        return  Repository.postavataravatar(two.getFile(),two.getAuth());
    });

    public LiveData<Activtyall> ActivityallLiveData = Transformations.switchMap(locationLiveData3, auth -> {//获取所有活动展示
        return  Repository.getActivity(auth);
    });
    public LiveData<ActivityOne> ActivityOneLiveDate = Transformations.switchMap(locationLiveData4, two -> {//由一个id得到一个信息的详细信息
        return  Repository.getOneActivity(two.getData1(),two.getData2());
    });

    public LiveData<Postactivity> postactivityLiveData= Transformations.switchMap(locationLiveData5, two -> {//由一个id得到一个信息的详细信息
        signUpActivityVo signUpActivityVo = new signUpActivityVo(two.getData1());
        return  Repository.postActivity(signUpActivityVo,two.getData2());
    });
    public LiveData<Imageview>goodviewLiveData = Transformations.switchMap(locationLiveData6, auth -> {
        return  Repository.getGoodView(auth);
    });
    public LiveData<Deletecommentld>DeletecommentldLiveData = Transformations.switchMap(locationLiveData7, three -> {
        return  Repository.Deletecommentld(three.getData1(),three.getData2(),three.getData3());
    });
    public LiveData<GetCommentld> getCommentldLiveData = Transformations.switchMap(locationLiveData8, three -> {
        return  Repository.getNewComments(three.getData1(),three.getData2(),three.getData3());
    });
    public LiveData<VideoMessage> getNewCommentNumsLiveData= Transformations.switchMap(locationLiveData9, two -> {
        return  Repository.getNewCommentNums(two.getData1(),two.getData2());
    });
    /**
     * 点赞处理
     * 视频id
     * auth
     */
    public LiveData<Numdeals> goodDealLiveData= Transformations.switchMap(locationLiveData10, two -> {
        return  Repository.goodDeal(two.getData1(),two.getData2());
    });
    /**
     * 收藏处理
     * 视频id
     * mode=0点赞=1取消点赞
     * auth
     */
    public LiveData<Numdeals> likeDealLiveData= Transformations.switchMap(locationLiveData11, three -> {
        return  Repository.likeDeal(three.getData1(),three.getData2(),three.getData3());
    });

    public LiveData<ActivityUsers> getUserACtivityLiveData = Transformations.switchMap(locationLiveData12, two -> {//获取所有活动展示
        return  Repository.getUSerActivity(two.getData1(),two.getData2());
    });

    public LiveData<Commentlikes> CommentlikesLiveData = Transformations.switchMap(locationLiveData13, five -> {//获取所有活动展示
        return  Repository.getCommentLike(five.getData1(),five.getData2(),five.getData3(),five.getData4());
    });
    public LiveData<publishCommentVo> postCommentLiveData = Transformations.switchMap(locationLiveData14, two -> {//获取所有活动展示
        return  Repository.postComment(two.getData1(),two.getData2());
    });
    public LiveData<Message> getUserMessLiveData = Transformations.switchMap(locationLiveData15, auth -> {//获取所有活动展示
        return  Repository.getUserMess(auth);
    });
    public LiveData<Name> reviseUserMessLiveData = Transformations.switchMap(locationLiveData16, two -> {//获取所有活动展示
        return  Repository.reviseUserMess(two.getData1(),two.getData2());
    });
    public LiveData<OlderBind> GetOlderBindLiveData = Transformations.switchMap(locationLiveData17, auth -> {//获取所有活动展示
        return  Repository.getOlerBind(auth);
    });
    public LiveData<Binddat> PostOlderBindLiveData = Transformations.switchMap(locationLiveData18, OlderBind -> {//获取所有活动展示
        return  Repository.PostOlerBind(OlderBind.getNameAndIdentifyId(),OlderBind.getAuth());
    });
    public LiveData<ShowData> DataDetailLiveData = Transformations.switchMap(locationLiveData19, two -> {//获取所有活动展示
        return  Repository.getOlerDataDetail(two.getData1(),two.getData2());
    });
   public LiveData<Nurse> PostNurseLiveData = Transformations.switchMap(locationLiveData20, two -> {//获取所有活动展示
        return  Repository.Postnurse(two.getData1(),two.getData2());
    });


    public void  getolder (String auth) {
        locationLiveData.postValue(auth);
    }//老人的基本信息
    public void getavatar(String auth) {
        locationLiveData1.postValue(auth);
    }//老人的头像

    public void postAvaterPhoto(File file, String auth) {//老人更换头像，提交头像
        Two1 two = new Two1(file,auth);
        locationLiveData2.postValue(two);
    }

    public void getActivity(String auth) {
        locationLiveData3.postValue(auth);
    }//获取所有活动展示

    public void getOneActivity(String id, String auth) {//由一个id得到一个信息的详细信息
        Two two = new Two(id,auth);
        locationLiveData4.postValue(two);
    }

    public void postActivity(String id, String auth) {//一个id报名活动
        Two two = new Two(id,auth);
        locationLiveData5.postValue(two);
    }

    public void getGoodView(String auth) {
        locationLiveData6.postValue(auth);
    }//获取所有收藏的视频
    /**
     * @param auth
     * @param videold
     * @param commentld
     * @return  删除评论
     */
    public void Deletecommentld(String auth,String videold,String commentld) {
        Three three =new Three(auth,videold,commentld);
        locationLiveData7.postValue(three);
    }
    /**
     * @param auth
     * @param videold
     * @param pageNum
     * @return 获取评论
     */
    public void  getNewComments (String auth, String videold, String pageNum) {
        Three three =new Three(auth,videold,pageNum);
        locationLiveData8.postValue(three);
    }
    /**
     * @param auth
     * @param videold 视频id
     * @return 根据id获取评论点赞数
     */
    public void getNewCommentNums(String videold,String auth) {
        Two two = new Two(videold,auth);
        locationLiveData9.postValue(two);
    }
    public void goodDeal(String videold,String auth) {
        Two two = new Two(videold,auth);
        locationLiveData10.postValue(two);
    }
    public void likeDeal(String videold,String mode,String auth) {
        Three three = new Three(videold,mode,auth);
        locationLiveData11.postValue(three);
    }
    public void getUserActivity(String auth,String status) {
        Two two = new Two(auth,status);
        locationLiveData12.postValue(two);
    }
    public void getCommentLike(String videoId,String commentId, String mode, String auth)
    {
        Five five = new Five(videoId,commentId,mode,auth);
        locationLiveData13.postValue(five);
    }
    public void postComment(publishCommentVo publishCommentVo1, String auth)
    {
        Comm comm = new Comm(publishCommentVo1,auth);
        locationLiveData14.postValue(comm);
    }
    public void getUserMess(String auth)
    {
        locationLiveData15.postValue(auth);
    }
    public void  reviseUserMess(userAvatarVo serAvatarVo, String auth)
    {
        Mss mss = new Mss(serAvatarVo,auth);
        locationLiveData16.postValue(mss);
    }
    public void getOlerBind(String auth)
    {
        locationLiveData17.postValue(auth);
    }
    public void PostOlerBind(nameAndIdentifyId nameAndIdentifyId, String auth)
    {
        BinOlder binOlder = new BinOlder(nameAndIdentifyId,auth);
        locationLiveData18.postValue(binOlder);
    }
    public void getOlerDataDetail(String auth,String id)
    {
       Two two = new Two(auth,id);
        locationLiveData19.postValue(two);
    }
    public void Postnurse(String content,String auth)
    {
        Two two = new Two(content,auth);
        locationLiveData20.postValue(two);
    }
}

