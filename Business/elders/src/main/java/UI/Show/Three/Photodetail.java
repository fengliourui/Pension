package UI.Show.Three;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.Base.main.Service.Data.older.Postavaterphoto;
import com.example.Base.main.Service.ViewModel.OlderViewModel;
import com.example.Business.elders.MainActivity1;
import android.Manifest;
import com.example.Business.elders.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class Photodetail extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private Uri selectedImageUri; // 用于保存选中的图片URI

    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    Button buttom1;
    Button buttom2;
    Button buttom3;
    View bottomSheetView;

     OlderViewModel olderViewModel;
    private  File imageFile;
    public BottomSheetDialog bottomSheetDialog;//底层抽屉

    String   mCurrentPhotoPath;

    static final int REQUEST_TAKE_PHOTO = 2;
    private static final int MY_PERMISSIONS_REQUEST = 1; // 或者你可以选择任何其他的整数值作为请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photodetail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String imagePath = getIntent().getStringExtra("imagePath");
        //处理底边抽屉
        dealbottomSheet();
        //获取实例
        initView();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        Glide.with(this).load(imagePath).into(imageView2);
        //获取服务端数据
        getCloudData();

        //点击事件
        initClick();
    }

    private void dealbottomSheet() {
        // 初始化 BottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(Photodetail.this);
        bottomSheetDialog.setContentView(R.layout.bottonlayout);
        // 设置点击外部区域关闭
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        if (bottomSheetDialog != null && bottomSheetDialog.getWindow() != null) {
            bottomSheetView = bottomSheetDialog.findViewById(R.id.bottomsheet);
        }
    }

    private void getCloudData() {
        olderViewModel = new ViewModelProvider(this).get(OlderViewModel.class);
        olderViewModel.PoatPhotoLiveData.observe(this, new Observer<Postavaterphoto>() {
            @Override
            public void onChanged(Postavaterphoto postavaterphoto) {
                if (postavaterphoto != null) {
                    String code = postavaterphoto.getCode();
                    if (code != null && "success".equals(code)) {
                        Toast.makeText(Photodetail.this, "上传成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Photodetail.this, postavaterphoto.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Photodetail.this, "上传失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initView() {
        imageView= findViewById(R.id.backButton);
        imageView1= findViewById(R.id.moreButton);
        imageView2= findViewById(R.id.qwe);

        bottomSheetView = bottomSheetDialog.findViewById(R.id.bottomsheet);
        buttom1=bottomSheetView.findViewById(R.id.bottom1);
        buttom2=bottomSheetView.findViewById(R.id.bottom2);
        buttom3=bottomSheetView.findViewById(R.id.bottom3);
    }

    private void initClick() {
        imageView= findViewById(R.id.backButton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });
        buttom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        //请求权限
                  requestPermissions();
            }
        });
        buttom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        buttom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                //展示活动
                selectedImageUri = data.getData();
                String s=selectedImageUri.toString();
                // 使用选中的图片URI进行操作，如显示到ImageView中
                // ...
                Glide.with(this).load(s).into(imageView2);
                Glide.with(this)
                        .downloadOnly()
                        .load(s)
                        .into(new CustomTarget<File>() {
                            @Override
                            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                // 下载完成后的处理
                                // resource即为下载后的File文件
                                // 在这里可以进行文件操作，比如复制、移动等
                                // 例如，将文件复制到指定目录
                                File cacheDir = getCacheDir();
                                String fileName = "cached_image1.jpg";
                                imageFile = new File(cacheDir, fileName);
                                try {
                                    MainActivity1.copyFile(resource, imageFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                olderViewModel.postAvaterPhoto(imageFile, MainActivity1.auth);
                                // 创建一个Intent对象
                                Intent intent = new Intent();
                                String imagePath = imageFile.getAbsolutePath();
                                intent.putExtra("imagePath2",imagePath);
                                // 设置结果码为RESULT_OK，表示操作成功
                                setResult(Activity.RESULT_OK, intent);
                            }
                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                                // 可选的清理操作
                            }
                        });
//               使用Glide加载图片并获取Bitmap对象
                Glide.with(this)
                        .asBitmap()
                        .load(s);

            }
        }
        //拍照结果处理
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            // 可以使用mCurrentPhotoPath路径下的图片文件了
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
            imageView2.setImageBitmap(bitmap);
        // 定义您想要保存的新文件名
            String fileName = "cached_image1.jpg";
         // 调用保存方法
            imageFile = saveBitmapToCacheDir(this, bitmap, fileName);
            olderViewModel.postAvaterPhoto(imageFile, MainActivity1.auth);
            // 创建一个Intent对象
            Intent intent = new Intent();
            String imagePath = imageFile.getAbsolutePath();
            intent.putExtra("imagePath2",imagePath);
            // 设置结果码为RESULT_OK，表示操作成功
            setResult(Activity.RESULT_OK, intent);
        }

    }
    //唯一的文件名用于保存图片
    private File createImageFile() throws IOException {
        // 创建一个唯一的文件名
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* 前缀 */
                ".jpg",         /* 后缀 */
                storageDir      /* 文件夹 */
        );

        // 保存文件: path 用于后面访问图片
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    //启动相机
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 确保有相机活动来处理Intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // 创建文件用于保存图片
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // 错误处理
            }
            // 如果文件被成功创建
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.Business.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    //权限方法的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限被授予，可以拍照
                dispatchTakePictureIntent();
            } else {
                // 权限被拒绝，给用户提示
            }
        }
    }
    //像用户发起相机和存储权限请求
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
        }
    }
    public File saveBitmapToCacheDir(Context context, Bitmap bitmap, String fileName) {
        // 获取应用的缓存目录
        File cacheDir = context.getCacheDir();
        // 创建一个新的文件，使用提供的文件名
        File imageFile = new File(cacheDir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);
            // 假设我们保存为JPEG格式，可以根据需要选择PNG等格式
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            return imageFile; // 返回新保存文件的引用
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 发生错误时返回null
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}