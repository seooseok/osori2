package com.osori.cave.utils

//License by https://github.com/phxql/kotlin-crypto-example/blob/master/src/main/kotlin/de/mkammerer/Crypto.kt

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Be sure to use a SecureRandom!
 */
val secureRandom = SecureRandom()

class Crypto(private val key: String) {

    private val iv = "6c5577c69ceabcce".toByteArray()

    fun enc(plaintext: String): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")

        val ivSpec = IvParameterSpec(iv)

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

        return cipher.doFinal(plaintext.toByteArray(Charsets.UTF_8))
    }

    fun dec(cipherText: ByteArray): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val ivSpec = IvParameterSpec(iv)

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)

        return String(cipher.doFinal(cipherText), Charsets.UTF_8)
    }
}

/**
 * Generates a key with [sizeInBits] bits.
 */
fun generateKey(sizeInBits: Int): ByteArray {
    val result = ByteArray(sizeInBits / 8)
    secureRandom.nextBytes(result)
    return result
}

/**
 * Generates an IV. The IV is always 128 bit long.
 */
fun generateIv(): ByteArray {
    val result = ByteArray(128 / 8)
    secureRandom.nextBytes(result)
    return result
}

/**
 * Generates a nonce for GCM mode. The nonce is always 96 bit long.
 */
fun generateNonce(): ByteArray {
    val result = ByteArray(96 / 8)
    secureRandom.nextBytes(result)
    return result
}
