package com.softulp.loginsharedpref.main.ui.register;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.loginsharedpref.R;
import com.softulp.loginsharedpref.databinding.ActivityRegisterBinding;
import com.softulp.loginsharedpref.main.ui.model.Usuario;
import com.softulp.loginsharedpref.main.ui.utils.InputError;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegisterActivityViewModel.class);
        setContentView(binding.getRoot());

        vm.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.tvTitulo.setText(R.string.editando_usuario);
                binding.nombreEditText.setText(usuario.getNombre());
                binding.apellidoEditText.setText(usuario.getApellido());
                binding.dniEditText.setText(usuario.getDni()+"");
                binding.emailEditText.setText(usuario.getEmail());
                binding.passwordEditText.setText(usuario.getPassword());
            }
        });

        vm.getDatos(getIntent());

        vm.getInputErrors().observe(this, new Observer<InputError>() {
            @Override
            public void onChanged(InputError inputError) {
                binding.textInputLayoutNombre.setError(inputError.getNombreError());
                binding.textInputLayoutApellido.setError(inputError.getApellidoError());
                binding.textInputLayoutDni.setError(inputError.getDniError());
                binding.textInputLayoutEmail.setError(inputError.getEmailError());
                binding.textInputLayoutPassword.setError(inputError.getPasswordError());
            }
        });

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre, apellido, dni, email, password;
                nombre = binding.nombreEditText.getText().toString();
                apellido = binding.apellidoEditText.getText().toString();
                dni = binding.dniEditText.getText().toString();
                email = binding.emailEditText.getText().toString();
                password = binding.passwordEditText.getText().toString();
                vm.guardarDatos(nombre, apellido, dni, email, password);
            }
        });

    }
}