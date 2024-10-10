package com.sunaa.callmedady.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunaa.callmedady.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )



    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
private val fino = FontFamily(
    Font(R.font.fino_bold, weight = FontWeight.W500),
    Font(R.font.safaniah, weight = FontWeight.W400),
    Font(R.font.gs, weight = FontWeight.W300)
)

val finoTypograhy = Typography(
    headlineMedium = TextStyle(
        fontFamily = fino,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
        shadow = Shadow(color = Color.DarkGray)
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fino,
        fontWeight = FontWeight.W400,
        fontSize = 26.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fino,
        fontWeight = FontWeight.W300,
        fontSize = 20.sp
    )
)