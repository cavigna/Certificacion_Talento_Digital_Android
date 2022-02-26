package com.cavigna.talentodigital.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.cavigna.talentodigital.R
import com.cavigna.talentodigital.databinding.FragmentDetailsBinding
import com.cavigna.talentodigital.databinding.FragmentHomeBinding
import com.cavigna.talentodigital.model.models.CoinDetails
import java.text.NumberFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


fun sendEmailIntent(coinDetails: CoinDetails, context: Context) {

    val i = Intent(Intent.ACTION_SEND)
    i.type = "message/rfc822"

    i.putExtra(Intent.EXTRA_EMAIL, arrayOf(" Info@cryptoinvest.cl"))
    i.putExtra(Intent.EXTRA_SUBJECT, ": Solicito información sobre  ${coinDetails.id}")
    i.putExtra(
        Intent.EXTRA_TEXT, "“Hola\n" +
                "Quisiera pedir información sobre este moneda ${coinDetails.name}, " +
                "me gustaría que me contactaran a\n" +
                "este correo o al siguiente número _________\n" +
                "Quedo atento.”\n"
    )

    try {
        context.startActivity(Intent.createChooser(i, "Send mail..."))
    } catch (ex: ActivityNotFoundException) {
        Toast.makeText(
            context,
            R.string.error_email,
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun precioUSD(numero: Int): String = "Precio: USD ${String.format("%.2f", numero.toFloat())}"

fun redondeo(numero: String):String{
    return String.format("%.2f", numero.toFloat())
}

fun loadingHome(loading: Boolean, binding: FragmentHomeBinding) {
    binding.apply {
        when (loading) {
            true -> {
                progressBarHome.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            false -> {
                progressBarHome.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }
}

fun loadingDetails(loading: Boolean, binding: FragmentDetailsBinding) {
    binding.apply {
        when (loading) {
            true -> {
                progressBar.visibility = View.VISIBLE
                appbar.visibility = View.GONE
                scrollViewDetails.visibility = View.GONE
            }
            false -> {
                progressBar.visibility = View.GONE
                appbar.visibility = View.VISIBLE
                scrollViewDetails.visibility = View.VISIBLE
            }
        }
    }
}

fun createCircularProgressBar(contex: Context): CircularProgressDrawable {
    val circularProgres = CircularProgressDrawable(contex)
    circularProgres.strokeWidth = 10f
    circularProgres.centerRadius = 60f
    circularProgres.start()
    return circularProgres
}

fun ImageView.loadSvg(url: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .placeholder(createCircularProgressBar(this.context))
        .crossfade(500)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}

fun toCurrency(precio:Int): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("US", "CL"))
    numberFormat.maximumFractionDigits =2
    return numberFormat.format(precio)
}

fun parseDate(fecha: String): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val dateTime: ZonedDateTime = ZonedDateTime.parse(fecha) // date object
        val formatter2: DateTimeFormatter =
            DateTimeFormatter.ofPattern("EE, MMM d: HH:mm") // if you want to convert it any other format
        dateTime.format(formatter2)

    }else{
        fecha
    }
}




