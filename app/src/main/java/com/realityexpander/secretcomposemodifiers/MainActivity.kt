package com.realityexpander.secretcomposemodifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MagnifierStyle
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.realityexpander.secretcomposemodifiers.ui.theme.SecretComposeModifiersTheme

/**
 * Compose Tips - Secret Compose Modifiers
 *
 * Join the KMP Developers Group:
 * Twitter/X: https://twitter.com/i/communities/1739883885658607808
 * FaceBook: https://www.facebook.com/groups/913147236893976
 * LinkedIn: https://www.linkedin.com/groups/14367209
 */

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecretComposeModifiersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Marquee Text
                    if(true) {
                        Text(
                            text = "A very long line of text that will wrap not to the next line and beyond, I repeat, a very long line of text that will not wrap to the next line and beyond.",
                            maxLines = 1,
                            //modifier = Modifier.basicMarquee()
                        )
                    }

                    // Magnifier
                    if(false) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .magnifier(
                                    sourceCenter = {
                                        Offset(x = 200f, y = 250f)
                                    }
                                )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.kmp_logo),
                                contentDescription = "Android Logo",
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }

                    // Magnifier with drag
                    if(false) {
                        var offset by remember {
                            mutableStateOf(Offset.Zero)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(true) {
                                    detectDragGestures { change, dragAmount ->
                                        change.consume()
                                        offset += dragAmount
                                    }
                                }
                                .magnifier(
                                    sourceCenter = {
                                        offset
                                    },
                                    magnifierCenter = {
                                        offset - Offset(
                                            x = 0f,
                                            y = 200f
                                        ) // display the magnifier 200 pixels above the touch point
                                    },
                                    style = MagnifierStyle(
                                        size = DpSize(200.dp, 200.dp),
                                        cornerRadius = 100.dp
                                    )
                                )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.kmp_logo),
                                contentDescription = "Android Logo",
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }

                    // Draw with content (no need to use Canvas)
                    if(false) {
                        var offset by remember {
                            mutableStateOf(Offset.Zero)
                        }
                        val color = MaterialTheme.colorScheme.primary
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .pointerInput(true) {
                                    detectDragGestures { change, dragAmount ->
                                        change.consume()
                                        offset += dragAmount
                                    }
                                }
                                .drawWithContent {
                                    drawContent() // draws the background content
                                    drawCircle(
                                        center = offset,
                                        color = color,
                                        radius = 100f
                                    )
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.kmp_logo),
                                contentDescription = "Android Logo",
                                modifier = Modifier.fillMaxWidth(),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }

                }
            }
        }
    }
}
