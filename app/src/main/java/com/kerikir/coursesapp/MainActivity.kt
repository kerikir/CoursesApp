package com.kerikir.coursesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MyUI()
                }
            }
        }
    }
}

object AppColor {
    val Purple = Color(0xFF521C98)
    val LightPurple = Color(0xFFF0E9FA)
    val BgGray = Color(0xFFF8F8F8)
    val TextGray = Color(0xFF8E8E8E)
    val OverlayBlack = Color(0x90000000)
}

data class CourseItem(
    val title: String,
    val name: String,
    val price: Int,
    val score: Double,
    val picUrl: Int
)

data class CategoryItem(
    val title: String,
    val icon: Int
)

data class BottomMenuItem(
    val label: String,
    val icon: Int
)

@Composable
fun MyUI() {
    Scaffold(
        bottomBar = { MyBottomBar() },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            SearchRow()
            Banner()
            Categories()
            PopularCoursesHeaded()
            ItemList()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SearchRow() {
    var textState by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .border(1.dp, AppColor.Purple, RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (textState.isEmpty()) {
                Text(
                    text = "Search courses...",
                    color = AppColor.TextGray,
                    fontSize = 14.sp
                )
            }
            BasicTextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                    fontSize = 14.sp
                )
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(AppColor.LightPurple, RoundedCornerShape(10.dp))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "Notification Bell",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun Banner() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(160dp)
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .background(AppColor.Purple, RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        val (textRef, buttonRef, imageRef) = createRefs()

        Text(
            text = "Advanced certification\ncourses available",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(textRef) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        Box(
            modifier = Modifier
                .background(AppColor.LightPurple, RoundedCornerShape(6.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .constrainAs(buttonRef) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .clickable { }
        ) {
            Text(
                text = "Buy Now",
                color = AppColor.Purple,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }

        Image(
            painter = painterResource(id = R.drawable.girl2),
            contentDescription = "Banner Girl",
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun Categories() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Category",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = "See all",
                fontSize = 14.sp,
                color = AppColor.Purple,
                modifier = Modifier.clickable { }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        val categories = listOf(
            CategoryItem("Business", R.drawable.cat1),
            CategoryItem("Creative", R.drawable.cat2),
            CategoryItem("Coding", R.drawable.cat3),
            CategoryItem("Gaming", R.drawable.cat4)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            categories.forEach { category ->
                Column(
                    modifier = Modifier
                        .weight(0.25f)
                        .clickable { },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(AppColor.LightPurple, RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = category.icon),
                            contentDescription = category.title,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = category.title,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun PopularCoursesHeaded() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Popular Courses",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = "See all",
            fontSize = 14.sp,
            color = AppColor.Purple,
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun ItemList() {
    val courses = listOf(
        CourseItem("Fullstack Web Development", "John Doe", 49, 4.8, R.drawable.pic1),
        CourseItem("Advanced UI/UX Masterclass", "Jane Smith", 39, 4.9, R.drawable.pic2),
        CourseItem("Kotlin & Jetpack Compose", "Alex Johnson", 59, 4.7, R.drawable.pic1),
        CourseItem("Game Dev with Unity", "Chris Lee", 45, 4.6, R.drawable.pic2)
    )

    androidx.compose.foundation.lazy.LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(courses.size) { index ->
            val course = courses[index]
            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 280.dp)
                    .shadow(3.dp, RoundedCornerShape(10.dp))
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val (topImg, titleRef, ownerRef, priceRef, scoreRef) = createRefs()

                    Image(
                        painter = painterResource(id = course.picUrl),
                        contentDescription = course.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .constrainAs(topImg) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(AppColor.OverlayBlack)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .constrainAs(titleRef) {
                                bottom.linkTo(topImg.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    ) {
                        Text(
                            text = course.title,
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .constrainAs(ownerRef) {
                                top.linkTo(topImg.bottom, margin = 8.dp)
                                start.linkTo(parent.start)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = course.name,
                            fontSize = 12.sp,
                            color = AppColor.TextGray
                        )
                    }

                    Text(
                        text = "$${course.price}",
                        color = AppColor.Purple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.constrainAs(priceRef) {
                            top.linkTo(ownerRef.bottom, margin = 6.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                        }
                    )

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .constrainAs(scoreRef) {
                                bottom.linkTo(parent.bottom, margin = 8.dp)
                                end.linkTo(parent.end, margin = 8.dp)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Star",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${course.score}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyBottomBar() {
    val items = listOf(
        BottomMenuItem("Explorer", R.drawable.btn_1),
        BottomMenuItem("Wishlist", R.drawable.btn_2),
        BottomMenuItem("My Course", R.drawable.btn_3),
        BottomMenuItem("Account", R.drawable.btn_4)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    BottomAppBar(
        containerColor = AppColor.BgGray,
        tonalElevation = 8.dp
    ) {
        NavigationBar(
            containerColor = AppColor.BgGray,
            tonalElevation = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.label,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(text = item.label, fontSize = 10.sp) },
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AppColor.Purple,
                        selectedTextColor = AppColor.Purple,
                        unselectedIconColor = AppColor.TextGray,
                        unselectedTextColor = AppColor.TextGray,
                        indicatorColor = AppColor.LightPurple
                    )
                )
            }
        }
    }
}