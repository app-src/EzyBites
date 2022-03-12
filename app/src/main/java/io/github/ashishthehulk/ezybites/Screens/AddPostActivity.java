package io.github.ashishthehulk.ezybites.Screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import io.github.ashishthehulk.ezybites.MainActivity;
import io.github.ashishthehulk.ezybites.Models.PostModel;
import io.github.ashishthehulk.ezybites.R;

public class AddPostActivity extends AppCompatActivity {

    private ImageView backPost, uploadImage;
    private Button uploadBtn;
    private DatabaseReference root ;
    private StorageReference reference;
    private Uri imageUri;
    private FirebaseUser user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        backPost = findViewById(R.id.backPost);
        uploadImage = findViewById(R.id.uploadImage);
        uploadBtn = findViewById(R.id.uploadBtn);
        root = FirebaseDatabase.getInstance().getReference("Post");
        reference = FirebaseStorage.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        backPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
                intent.putExtra("frag",2);
                startActivity(intent);
                finish();

            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent gallery = new Intent();
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            gallery.setType("image/*");
            startActivityForResult(gallery, 2);

            }
        });


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imageUri!=null)
                {
                    uploadToFirebase(imageUri);
                }else
                {
                    Toast.makeText(AddPostActivity.this, "Please select an Image!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void uploadToFirebase(Uri uri)
    {
        String userId = user.getUid().toString();
        StorageReference fileRef = reference.child(userId+System.currentTimeMillis()+"."+getFileExtention(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        PostModel model = new PostModel(uri.toString());
                        String modelId = root.push().getKey();
                        root.child(modelId).setValue(model);
                        Toast.makeText(AddPostActivity.this, "Uploaded Successfully.", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(AddPostActivity.this, "Uploading failed!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error",e.getMessage());
            }
        });

    }

    private String getFileExtention(Uri mUri)
    {

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data != null)
        {
            imageUri = data.getData();
            uploadImage.setImageURI(imageUri);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
        intent.putExtra("frag",2);
        startActivity(intent);
        finish();

    }
}