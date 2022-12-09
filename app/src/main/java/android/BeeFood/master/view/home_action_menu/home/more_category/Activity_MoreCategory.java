package android.BeeFood.master.view.home_action_menu.home.more_category;

import android.BeeFood.master.R;
import android.BeeFood.master.controller.Dao.LoaiDao;
import android.BeeFood.master.model.loaiFood;
import android.BeeFood.master.view.home_action_menu.home.Adapter_Foodtype;
import android.BeeFood.master.view.home_action_menu.home.food.Activity_food;
import android.BeeFood.master.view.home_action_menu.home.recommended_for_you.Activity_Recommended;
import android.BeeFood.master.view.object.Loai_food;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class Activity_MoreCategory extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference reference = storage.getReference();

    private ArrayList<Loai_food> list_FoodType = new ArrayList<>();
    private Adapter_Foodtype adapter_FoodType;
    private GridView grid_home_ActionMenu_MoreCategory_list;
    private ImageView img_home_ActionMenu_MoreCategory_back,img_home_ActionMenu_MoreCategory_add,img_dialog_addFoodType_avt;

    Uri uri;
    String url_avt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_category);

        AnhXa();

        db.collection("loaifood")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<loaiFood> list = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                try {
                                    list.add(new loaiFood(document.getId(), document.getData().get("ImageUrl").toString(),document.getData().get("NameLoai").toString()));
                                } catch (Exception e) {

                                }
                            }
                        }
                        adapter_FoodType = new Adapter_Foodtype(Activity_MoreCategory.this,list,R.layout.home_item_foodtype);
                        grid_home_ActionMenu_MoreCategory_list.setAdapter(adapter_FoodType);

                        grid_home_ActionMenu_MoreCategory_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String sItem = list.get(i).getNameloai();

                                Intent intent_toFood = new Intent(Activity_MoreCategory.this, Activity_food.class);
                                intent_toFood.putExtra("key_FoodType",sItem);
                                intent_toFood.putExtra("key_chkSpecialMore",false);
                                startActivity(intent_toFood);
                            }
                        });
                    }
                });

//        adapter_FoodType = new Adapter_Foodtype(Activity_MoreCategory.this,list_FoodType,R.layout.home_item_foodtype);
        grid_home_ActionMenu_MoreCategory_list.setAdapter(adapter_FoodType);

        grid_home_ActionMenu_MoreCategory_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sItem = list_FoodType.get(i).getName();
                Intent intent_toFood = new Intent(Activity_MoreCategory.this, Activity_food.class);
                intent_toFood.putExtra("key_FoodType",sItem);
                intent_toFood.putExtra("key_chkSpecialMore",true);
                startActivity(intent_toFood);
                finish();
            }
        });

        img_home_ActionMenu_MoreCategory_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Activity_MoreCategory.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
                onBackPressed();
            }
        });

        img_home_ActionMenu_MoreCategory_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Activity_MoreCategory.this);
                dialog.setContentView(R.layout.dialog_add_foodtype);

                Window window = dialog.getWindow();
//                        window.setLayout();

                img_dialog_addFoodType_avt = dialog.findViewById(R.id.dialog_addFoodType_avt);
                EditText edt_dialog_addFoodType_name = dialog.findViewById(R.id.dialog_addFoodType_name);
                Button btn_dialog_addFoodType_cancel = dialog.findViewById(R.id.dialog_addFoodType_cancel);
                Button btn_dialog_addFoodType_Add = dialog.findViewById(R.id.dialog_addFoodType_Add);

                btn_dialog_addFoodType_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                btn_dialog_addFoodType_Add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = edt_dialog_addFoodType_name.getText().toString().trim();
                        if (name.isEmpty()){
                            Toast.makeText(Activity_MoreCategory.this,"not null", Toast.LENGTH_SHORT).show();
                        } else {
                            if(uri != null){
                                SharedPreferences sharedPref = getSharedPreferences("USER", MODE_PRIVATE);
                                String email = sharedPref.getString("email", "");

                                db.collection("loaifood")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                ArrayList<loaiFood> list = new ArrayList<>();
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        try {
                                                            if (name.equalsIgnoreCase(document.getData().get("NameLoai").toString().trim())){
                                                                Toast.makeText(Activity_MoreCategory.this, "Loại đã tồn tại", Toast.LENGTH_SHORT).show();
                                                                return;
                                                            }
                                                            list.add(new loaiFood(document.getId(), document.getData().get("ImageUrl").toString(),document.getData().get("NameLoai").toString()));
                                                        } catch (Exception e) {
                                                        }
                                                    }
                                                }

                                                StorageReference demoRef = reference.child(email+"."+ list.size()+".loai.png");
                                                demoRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                    @Override
                                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                        Toast.makeText(getApplication(), "Uploaded", Toast.LENGTH_SHORT).show();
                                                        demoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri DownloadUri) {
                                                                url_avt = DownloadUri.toString();
                                                                loaiFood loaiFood = new loaiFood(url_avt,name);
                                                                LoaiDao loaiDao = new LoaiDao();
                                                                boolean check = loaiDao.addDataLoaiFood(loaiFood, Activity_MoreCategory.this);
                                                                Toast.makeText(Activity_MoreCategory.this, "Thêm loại thành công", Toast.LENGTH_SHORT).show();
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });


                            }
                        }
                    }
                });

                img_dialog_addFoodType_avt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 174);
                    }
                });

                dialog.show();
            }
        });

    }

    public void AnhXa(){
        grid_home_ActionMenu_MoreCategory_list = findViewById(R.id.home_ActionMenu_MoreCategory_list);
        img_home_ActionMenu_MoreCategory_back = findViewById(R.id.home_ActionMenu_MoreCategory_back);
        img_home_ActionMenu_MoreCategory_add = findViewById(R.id.home_ActionMenu_MoreCategory_add);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 174) {
            uri = data.getData();
            Glide.with(getApplication()).load(uri).into(img_dialog_addFoodType_avt);
        }
    }
}