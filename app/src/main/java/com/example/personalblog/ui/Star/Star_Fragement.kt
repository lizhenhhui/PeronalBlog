package com.example.personalblog.ui.Star

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.personalblog.R
import java.io.File

class Star_Fragement :Fragment(){
    val viewModel by lazy { ViewModelProvider(this)[StarViewModel::class.java] }
    lateinit var takePhotoBtn:Button
    lateinit var fromAlumBtn:Button
    lateinit var imageView: ImageView
    lateinit var imageUri:Uri
    lateinit var outputImage: File
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragemnt_star,container,false)
        takePhotoBtn=view.findViewById(R.id.takePhotoBtn)
        imageView=view.findViewById(R.id.photo_image)
        fromAlumBtn=view.findViewById(R.id.fromAlbumBtn)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        takePhotoBtn.setOnClickListener{
            outputImage= File(activity?.externalCacheDir,"output_image.jpg")
            if(outputImage.exists()){
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri=if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                FileProvider.getUriForFile(activity!!,"com.example.personalblog.fileprovider",outputImage)
            }else{
                Uri.fromFile(outputImage)
            }
            //启动相机程序
            val intent= Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,1)
        }
        fromAlumBtn.setOnClickListener{
            //打开文件选择器
            val intent=Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            //指定只显示图片
            intent.type="image/*"
            startActivityForResult(intent,2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                if (resultCode== Activity.RESULT_OK){
                    //将拍摄的照片显示出来
                    val bitmap=BitmapFactory.decodeStream(activity!!.contentResolver.openInputStream(imageUri))
                    imageView.setImageBitmap(rotateIfRequired(bitmap))
                }
            }
            2->{
                if (resultCode==Activity.RESULT_OK&&data!=null){
                    data.data?.let { uri ->
                        //将选择的图片显示
                        val bitmap=getBitmapFromUri(uri)
                        imageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
    private fun getBitmapFromUri(uri:Uri)=activity!!.contentResolver.openFileDescriptor(uri,"r").use {
        BitmapFactory.decodeFileDescriptor(it?.fileDescriptor)
    }
    private fun rotateIfRequired(bitmap: Bitmap):Bitmap{
        val exif=ExifInterface(outputImage.path)
        val orientation=exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)
        return when(orientation){
            ExifInterface.ORIENTATION_ROTATE_90->rotateBitmap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_180->rotateBitmap(bitmap,180)
            ExifInterface.ORIENTATION_ROTATE_270->rotateBitmap(bitmap,270)
            else ->bitmap
        }
    }
    private fun rotateBitmap(bitmap: Bitmap,degree:Int):Bitmap{
        val matrix=Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
        bitmap.recycle()
        return rotatedBitmap
    }
}