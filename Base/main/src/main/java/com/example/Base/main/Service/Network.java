package com.example.Base.main.Service;

import android.util.Log;

import com.example.Base.main.Service.Data.Activity.ActivityOne;
import com.example.Base.main.Service.Data.Activity.ActivityUsers;
import com.example.Base.main.Service.Data.Activity.ActivtyUser;
import com.example.Base.main.Service.Data.Activity.Activtyall;
import com.example.Base.main.Service.Data.Activity.Postactivity;
import com.example.Base.main.Service.Data.Activity.signUpActivityVo;
import com.example.Base.main.Service.Data.Health.AllData;
import com.example.Base.main.Service.Data.Health.ShowData;
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
import com.example.Base.main.Service.Data.photo.GoodVIew;
import com.example.Base.main.Service.Data.photo.Image;
import com.example.Base.main.Service.Data.photo.Text;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;


public class Network {

    public static String message;
    private static Service Service = ServiceCreate.create(Service.class);

    public static Image getlmage(String tag, String num, String auth) {
        try {
            Response<Image> response = Service.getlmage(tag,num,auth).execute();
            if (response.isSuccessful()) {
                return  response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Text gettext(String id, String auth) {
        try {
            Response<Text> response = Service.gettext(id,auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    public static Older getolder(String auth) {
        try {
            Response<Older> response = Service.getolder(auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    public static Avatar getavatar(String auth) {
        try {
            Response<Avatar> response = Service.getavatar(auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    public static Postavaterphoto postavatar(File file, String auth)  {
        try {
            // 创建文件部分
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part avatarPart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            // 创建其他参数部分
            RequestBody authPart = RequestBody.create(MediaType.parse("text/plain"), "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VyRHRvIjp7InVzZXJJZCI6ImIzZDZhNzY5LTU4ZjgtNDkxNS1iMzU3LWViYjVhNTYzYmMyOSIsImlkZW50aWZ5IjoiMyJ9LCJleHAiOjMyODM3NjU0OTF9.R39mCWr7SybcGfdVyPum3uNd6u-OZykDAz5hAfLG40s");
            // 调用 Retrofit 接口方法
            String authHeader = "Bearer " + auth;
            Response<Postavaterphoto> response = Service.postavatar(auth,avatarPart).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Activtyall getActivity(String auth) {
        try {
            Response<Activtyall> response = Service.getactivity(auth,"1000","1").execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    /**
     * @param auth 密钥
     * @param status 审核状态 0不通过  1审核中 2审核通过
     * @return
     */
    public static List<ActivtyUser> getUserActivity(String auth, String status) {
        try {
            Response<List<ActivtyUser>> response = Service.getUserActivity(auth,status).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    public static ActivityOne getOneActivity(String id, String auth) {
        try {
            Response<ActivityOne> response = Service.getOneActivity(id,auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }


    public static Postactivity postAcivity(signUpActivityVo postactivity, String auth) {
        try {
            Response<Postactivity> response = Service.postActivity(postactivity,auth).execute();
            if (response.isSuccessful()) {
                Log.d("tttt","Unexpected HTTP code: " + response.code());
                return response.body();
            } else {
                Log.d("tttt","Unexpected HTTP code: " + response.code());
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    /**
     * @param auth
     * @return 获取收藏的视频
     */
    public static GoodVIew getGoodView(String auth) {
        try {
            Response<GoodVIew> response = Service.getGoodView(auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    /**
     * @param auth
     * @param videold
     * @param commentld
     * @return  删除评论
     */
    public static Deletecommentld deletecommentld (String auth, String videold, String commentld) {
        try {
            Response<Deletecommentld> response = Service.deleteComments(auth,videold,commentld).execute();
            if (response.isSuccessful()) {
                Log.d("ttttt","Unexpected HTTP code: 删除成功" );
                return response.body();
            } else {
                Log.d("ttttt","删除Unexpected HTTP code: " + response.code());
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    /**
     * @param auth
     * @param videold
     * @param pageNum
     * @return 获取评论
     */
    public static GetCommentld getNewComments(String auth, String videold, String pageNum) {
        try {
            Response<GetCommentld> response = Service.getNewComments(auth,videold,pageNum).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    /**
     * @param auth
     * @param videold 视频id
     * @return 根据id获取评论点赞数
     */
    public static VideoMessage getNewCommentNums(String videold, String auth) {
        try {
            Response<VideoMessage> response = Service.getNewCommentNums(videold,auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Numdeals goodDeal(String videold, String auth) {
        try {
            Response<Numdeals> response = Service.goodDeal(videold, auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Numdeals likeDeal(String videold, String mode, String auth) {
        try {
            Response<Numdeals> response = Service.likeDeal(videold,mode, auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }

    public static Commentlikes getCommentLike(String videoId, String commentId, String mode, String auth) {
        try {
            Response<Commentlikes> response = Service.getCommentLike(videoId,commentId,mode,auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static publishCommentVo postComment(publishCommentVo publishCommentVo, String auth) {
        try {
            Response<publishCommentVo> response = Service.postComment(publishCommentVo,auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Message getUserMess(String auth) {
        try {
            Response<Message> response = Service.getUserMess(auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Name reviseUserMess(userAvatarVo serAvatarVo  , String auth) {
        try {
            Response<Name> response = Service.reviseUserMess(auth,serAvatarVo).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static OlderBind getOlerBind(String auth) {
        try {
            Response<OlderBind> response = Service.getOlerBind(auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Binddat postOlerBind(nameAndIdentifyId nameAndIdentifyId, String auth) {
        try {
            Response<Binddat> response = Service.postolderBind(nameAndIdentifyId,auth).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static AllData getOlderData() {
        try {
            Response<AllData> response = Service.getOlerData().execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static ShowData getOlerDataDetail(String auth, String id) {
        try {
            Response<ShowData> response = Service.getOlerDataDetail(auth,id).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
    public static Nurse Postnurse(String requestData,String auth) {
        try {
            Response<Nurse> response = Service.postnurse(requestData,auth).execute();
            Log.d("ttttt",requestData );
            Log.d("ttttt",auth);
            if (response.isSuccessful()) {
                return response.body();
            } else {
                Log.d("ttttt","Unexpected HTTP code: " + response.code());
                throw new IOException("Unexpected HTTP code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 或者抛出适当的异常
        }
    }
}
