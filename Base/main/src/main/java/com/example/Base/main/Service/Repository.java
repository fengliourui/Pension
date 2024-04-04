package com.example.Base.main.Service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Base.main.Service.Data.Activity.ActivityOne;
import com.example.Base.main.Service.Data.Activity.Activtyall;
import com.example.Base.main.Service.Data.Activity.Postactivity;
import com.example.Base.main.Service.Data.Activity.SignUpActivityVo;
import com.example.Base.main.Service.Data.add.Binddat;
import com.example.Base.main.Service.Data.add.Commentlikes;
import com.example.Base.main.Service.Data.add.Deletecommentld;
import com.example.Base.main.Service.Data.add.GetCommentld;
import com.example.Base.main.Service.Data.add.Numdeals;
import com.example.Base.main.Service.Data.add.OlderBind;
import com.example.Base.main.Service.Data.add.VideoMessage;
import com.example.Base.main.Service.Data.add.nameAndIdentifyId;
import com.example.Base.main.Service.Data.add.publishCommentVo;
import com.example.Base.main.Service.Data.older.Avatar;
import com.example.Base.main.Service.Data.older.Message;
import com.example.Base.main.Service.Data.older.Name;
import com.example.Base.main.Service.Data.older.Older;
import com.example.Base.main.Service.Data.older.Postavaterphoto;
import com.example.Base.main.Service.Data.older.userAvatarVo;
import com.example.Base.main.Service.Data.photo.Good;
import com.example.Base.main.Service.Data.photo.GoodVIew;
import com.example.Base.main.Service.Data.photo.Image;
import com.example.Base.main.Service.Data.photo.Imageview;
import com.example.Base.main.Service.Data.photo.OssFileDTO;
import com.example.Base.main.Service.Data.photo.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Repository {

    // 获取图片
    public static LiveData<Imageview> getImageLiveData(String tag, String num, String auth) {
        MutableLiveData<Imageview> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Image images = Network.getlmage(tag, num, auth);
            List<Text> texts =null;
            if(images!=null)
            {
               texts = getlisttext(images,auth);
            }
            Imageview imageview = new Imageview(images,texts);
            liveData.postValue(imageview);
        }).start();
        return liveData;
    }
    //由图片集合中的ID获取一个文案集合
    public static List<Text> getlisttext(Image image,String auth)
    {
        List<OssFileDTO> ids = image.getData();
        List<Text> texts = new ArrayList<>();
        for (OssFileDTO item : ids) {
            try {
                String id = item.getId();
                Text text = Network.gettext(id, auth); // 假设该方法可能抛出异常
                texts.add(text);
            } catch (Exception e) {
                texts.add(null);
                // 处理异常，例如打印日志、中断处理或者继续
                e.printStackTrace();
            }
        }
        return texts;
    }

    public static LiveData<Older> getOlderLiveData(String auth) {
        MutableLiveData<Older> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Older older = Network.getolder(auth);
            liveData.postValue(older);
        }).start();
        return liveData;
    }

    public static LiveData<Avatar> getavataravatar(String auth) {
        MutableLiveData<Avatar> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Avatar avatar= Network.getavatar(auth);
            liveData.postValue(avatar);
        }).start();
        return liveData;
    }

    public static LiveData<Postavaterphoto> postavataravatar(File file, String auth) {
        MutableLiveData<Postavaterphoto> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Postavaterphoto postavaterphoto= Network.postavatar(file,auth);
            liveData.postValue(postavaterphoto);
        }).start();
        return liveData;
    }

    public static LiveData<Activtyall> getActivity(String auth) {
        MutableLiveData<Activtyall> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Activtyall activtyall= Network.getActivity(auth);
            liveData.postValue(activtyall);
        }).start();
        return liveData;
    }

    public static LiveData<Activtyall> getUSerActivity(String auth,String status) {
        MutableLiveData<Activtyall> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Activtyall activtyall= Network.getUserActivity(auth,status);
            liveData.postValue(activtyall);
        }).start();
        return liveData;
    }
    public static LiveData<ActivityOne> getOneActivity(String id, String auth) {
        MutableLiveData<ActivityOne> liveData = new MutableLiveData<>();
        new Thread(() -> {
            ActivityOne activityOne= Network.getOneActivity(id,auth);
            liveData.postValue(activityOne);
        }).start();
        return liveData;
    }

    public static LiveData<Postactivity> postActivity(SignUpActivityVo signUpActivityVo, String auth) {
        MutableLiveData<Postactivity> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Postactivity postavaterphoto= Network.postAcivity(signUpActivityVo,auth);
            liveData.postValue(postavaterphoto);
        }).start();
        return liveData;
    }
    /**
     * @param auth
     * @return 获取收藏的视频
     */
    public static LiveData<Imageview> getGoodView(String auth) {
        MutableLiveData<Imageview> liveData = new MutableLiveData<>();
        new Thread(() -> {
            GoodVIew goodVIew = Network.getGoodView(auth);
            List<OssFileDTO> ossFileDTOList = convertToOssFileDTOList(goodVIew.getData());
            Image image = new Image(goodVIew.getCode(),ossFileDTOList,goodVIew.getMessage());
            List<Text> texts =null;
            if(image!=null)
            {
                texts = getlisttext(image,auth);
            }
            Imageview imageview = new Imageview(image,texts);
            liveData.postValue(imageview);
        }).start();
        return liveData;
    }
    public static List<OssFileDTO> convertToOssFileDTOList(List<Good> goodList) {
        List<OssFileDTO> ossFileDTOList = new ArrayList<>();

        for (Good good : goodList) {
            OssFileDTO ossFileDTO = new OssFileDTO();
            ossFileDTO.setId(good.getVideoId());
            ossFileDTO.setUrl(good.getVideoUrl());
            ossFileDTOList.add(ossFileDTO);
        }

        return ossFileDTOList;
    }

    /**
     * @param auth
     * @param videold
     * @param commentld
     * @return  删除评论
     */
    public static LiveData<Deletecommentld> Deletecommentld(String auth, String videold, String commentld) {
        MutableLiveData<Deletecommentld> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Deletecommentld deletecommentld = Network.deletecommentld(auth,videold,commentld);
            liveData.postValue(deletecommentld);
        }).start();
        return liveData;
    }
    /**
     * @param auth
     * @param videold
     * @param pageNum
     * @return 获取评论
     */
    public static LiveData<GetCommentld> getNewComments(String auth, String videold, String pageNum) {
        MutableLiveData<GetCommentld> liveData = new MutableLiveData<>();
        new Thread(() -> {
            GetCommentld getCommentld = Network.getNewComments(auth,videold,pageNum);
            liveData.postValue(getCommentld);
        }).start();
        return liveData;
    }
    /**
     * @param auth
     * @param videold 视频id
     * @return 根据id获取评论点赞数
     */
    public static LiveData<VideoMessage> getNewCommentNums(String videold, String auth) {
        MutableLiveData<VideoMessage> liveData = new MutableLiveData<>();
        new Thread(() -> {
            VideoMessage videoMessage = Network.getNewCommentNums(videold,auth);
            liveData.postValue(videoMessage);
        }).start();
        return liveData;
    }
    public static LiveData<Numdeals> goodDeal(String videold, String auth) {
        MutableLiveData<Numdeals> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Numdeals numdeals = Network.goodDeal(videold,auth);
            liveData.postValue(numdeals);
        }).start();
        return liveData;
    }
    public static LiveData<Numdeals> likeDeal(String videold,String mode, String auth) {
        MutableLiveData<Numdeals> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Numdeals numdeals = Network.likeDeal(videold,mode,auth);
            liveData.postValue(numdeals);
        }).start();
        return liveData;
    }
    public static LiveData<Commentlikes> getCommentLike(String videoId, String commentId, String mode, String auth) {
        MutableLiveData<Commentlikes> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Commentlikes commentLike= Network.getCommentLike(videoId,commentId,mode,auth);
            liveData.postValue(commentLike);
        }).start();
        return liveData;
    }
    public static LiveData<publishCommentVo> postComment(publishCommentVo publishCommentVo1, String auth) {
        MutableLiveData<publishCommentVo> liveData = new MutableLiveData<>();
        new Thread(() -> {
            publishCommentVo publishCommentVo2= Network.postComment(publishCommentVo1,auth);
            liveData.postValue(publishCommentVo2);
        }).start();
        return liveData;
    }
    public static LiveData<Name> reviseUserMess(userAvatarVo serAvatarVo, String auth) {
        MutableLiveData<Name> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Name name= Network.reviseUserMess(serAvatarVo,auth);
            liveData.postValue(name);
        }).start();
        return liveData;
    }
    public static LiveData<Message> getUserMess(String auth) {
        MutableLiveData<Message> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Message message= Network.getUserMess(auth);
            liveData.postValue(message);
        }).start();
        return liveData;
    }
    public static LiveData<OlderBind> getOlerBind(String auth) {
        MutableLiveData<OlderBind> liveData = new MutableLiveData<>();
        new Thread(() -> {
            OlderBind name= Network.getOlerBind(auth);
            liveData.postValue(name);
        }).start();
        return liveData;
    }
    public static LiveData<Binddat> PostOlerBind(nameAndIdentifyId nameAndIdentifyId, String auth) {
        MutableLiveData<Binddat> liveData = new MutableLiveData<>();
        new Thread(() -> {
            Binddat name= Network.postOlerBind(nameAndIdentifyId,auth);
            liveData.postValue(name);
        }).start();
        return liveData;
    }
}
