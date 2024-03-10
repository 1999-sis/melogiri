package com.example.melogiri.controller;

import android.content.Intent;

import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.melogiri.util.SocketAPI;
import com.example.melogiri.view.LoginActivity;
import com.example.melogiri.view.RegisterActivity;

import java.util.concurrent.Executors;

public class ControllerRegister {
    SocketAPI socket = new SocketAPI("209.38.244.243", 8080);

    public void register(RegisterActivity registerActivity, String nome, String cognome, String data, String email, String password)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = socket.register(nome, cognome, data, email, password);
                    Log.d("REGISTER_UTENTE", response);
                    if (response.equalsIgnoreCase("Registration_Successful")) {
                        registerActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Show a Toast to indicate successful registration
                                Toast.makeText(registerActivity, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();

                                // Start an Intent to return to the previous Activity
                                Intent intent = new Intent(registerActivity, LoginActivity.class);
                                registerActivity.startActivity(intent);

                                // Close the current Activity
                                registerActivity.finish();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public void showBiometricPrompt(LoginActivity loginActivity)
    {
        BiometricManager biometricManager = BiometricManager.from(loginActivity);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("LOGIN_FACE", "Biometric authentication supported");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("LOGIN_FACE", "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("LOGIN_FACE", "Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e("LOGIN_FACE", "The user hasn't enrolled any biometrics.");
                break;
        }
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login with your face")
                .setSubtitle("Please use your biometric credentials to log in")
                .setConfirmationRequired(true)
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                .build();

        BiometricPrompt biometricPrompt = new BiometricPrompt(loginActivity,
                Executors.newSingleThreadExecutor(), new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // L'autenticazione è riuscita, puoi procedere con il login.
            }

            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                // Si è verificato un errore durante l'autenticazione.
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // L'autenticazione non è riuscita.
            }
        });

        biometricPrompt.authenticate(promptInfo);

    }
}
