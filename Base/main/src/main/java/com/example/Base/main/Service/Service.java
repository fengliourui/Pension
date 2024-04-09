package com.example.Base.main.Service;


import retrofit2.Call;

import com.example.Base.main.Service.Data.Activity.ActivityOne;
import com.example.Base.main.Service.Data.Activity.Activtyall;
import com.example.Base.main.Service.Data.Activity.Postactivity;
import com.example.Base.main.Service.Data.Activity.SignUpActivityVo;
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

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Service {
    @GET("/iv/getiv/tag")
    Call<Image> getlmage(//得到图片或者视频
                                   @Query("tag") String location,  // 必选参数：标签
                                   @Query("num") String apiKey  ,     // 必选参数：获取数量最多100
                                   @Query("auth") String auth      // 必选参数：登录令牌
    );


    @GET("/iv/get-headline")
    Call<Text> gettext(//由图片或者视频ID得到文案
                       @Query("id") String id, // 必选参数：查找id
                       @Query("auth") String auth      // 必选参数：登录令牌
    );

    @GET("/older/getolder")
    Call<Older> getolder(//获取老人信息
                         @Query("auth") String auth      // 必选参数：登录令牌
    );
    @GET("/user/get/avatar")
    Call<Avatar> getavatar(//用户头像
                           @Query("auth") String auth      // 必选参数：登录令牌
    );
    @Multipart
    @POST("/new/upload/avatar")
    Call<Postavaterphoto> postavatar(
            @Query("auth") String auth,
            @Part MultipartBody.Part file // 更改为MultipartBody.Part类型
    );
    @GET("/activity/getsome")
    Call<Activtyall> getactivity(//全部活动信息
                                 @Query("auth") String auth  ,    // 必选参数：登录令牌
                                 @Query("pagesize") String pagesize  ,
                                 @Query("pagenum") String pagenum
    );
    @GET("/activity/get/detail")
    Call<ActivityOne> getOneActivity(//获取一个活动信息
                                     @Query("id") String id  ,    // 必选参数：活动id
                                     @Query("auth") String  auth   // 必选参数：登录令牌
    );

    @POST("/activity/signup")
    @Headers({"accept: application/json","Content-Type: application/json"})
    Call<Postactivity> postActivity(//活动报名
                                    @Body SignUpActivityVo signUpActivityVo,  // 将请求体数据放在 URL 参数中
                                    @Query("auth") String auth
    );
    @GET("/new/get/video/collect")
    Call<GoodVIew> getGoodView(//获取收藏的视频
                               @Query("auth") String  auth   // 必选参数：登录令牌
    );

    @GET("/new/delete/comment")
    Call<Deletecommentld> deleteComments(//删除评论
                                         @Query("auth") String  auth,
                                         @Query("videold") String  videold,
                                         @Query("commentld") String  commentld
    );
    @GET("/new/get/comment")
    Call<GetCommentld> getNewComments(//根据id获取评论
                                      @Query("auth") String  auth,
                                      @Query("videoId") String  videold,
                                      @Query("pageNum") String  pageNum
    );
    @GET("/new/get/video")
    Call<VideoMessage> getNewCommentNums(//根据id获取评论点赞数
                                         @Query("videoId") String  videold,
                                         @Query("auth") String auth
    );

    @GET("/new/video/like")
    Call<Numdeals> likeDeal(//收藏的点击处理
                            @Query("videoId") String  videold,
                            @Query("mode") String mode,
                            @Query("auth") String auth
    );
    @GET("/new/video/collect")
    Call<Numdeals> goodDeal(//点赞的点击处理
                            @Query("videoId") String  videold,
                            @Query("auth") String auth
    );
    @GET("/new/get/act/by-status")
    Call<Activtyall> getUserActivity(//点赞的点击处理
            @Query("auth") String auth,
            @Query("status") String status
    );
    @GET("/new/video/comment/like")
    Call<Commentlikes> getCommentLike(//评论的点赞的点击处理
                                      @Query("videoId") String videoId,
                                      @Query("commentId") String commentId,
                                      @Query("mode") String mode,
                                      @Query("auth") String auth
    );
    @POST("/new/publish/comment")
    Call<publishCommentVo> postComment(//活动报名
                                       @Body publishCommentVo publishCommentVo,  // 将请求体数据放在 URL 参数中
                                       @Query("auth") String auth
    );
    @GET("/user/get/info")
    Call<Message> getUserMess(//获取用户信息
                              @Query("auth") String auth
    );
    @GET("/user/get/info")
    Call<Name> reviseUserMess(//修改用户昵称
                              @Query("auth") String auth,
                              @Query("userAvatarVo") userAvatarVo serAvatarVo
    );
    @GET("/family/get-bind/older")
    Call<OlderBind> getOlerBind(//获取已綁定老人的信息
                                @Query("auth") String auth
    );
    @POST("/family/bind/older")
    Call<Binddat> postolderBind(//家属提交绑定的老人
                                @Body nameAndIdentifyId nameAndIdentifyId,  // 将请求体数据放在 URL 参数中
                                @Query("auth") String auth
    );
    @GET("/older/getdatatype")
    Call<AllData> getOlerData(//获取老人数据类型
    );

    @GET("/older/getolder")
    Call<ShowData> getOlerDataDetail(//获取老人数据类型
                                     @Query("auth") String auth,
                                     @Query("olderid") String id
    );
    @POST("/nursing/upload/older")
    Call<Nurse> postnurse(//活动报名
                            @Body String requestData,  // 将请求体数据放在 URL 参数中
                            @Query("auth") String auth
    );
}
