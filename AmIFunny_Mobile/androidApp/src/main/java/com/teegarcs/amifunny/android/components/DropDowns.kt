package com.teegarcs.amifunny.android.components

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teegarcs.amifunny.android.R
import com.teegarcs.amifunny.android.theme.FunnyTheme

@Composable
fun FunnyDropDown(
    @StringRes dropDownLabel: Int,
    dropDownList: List<String>,
    defaultSelected: Int = -1,
    dropDownTitles: List<String?>? = null,//optional titles to show on per line basis
    modifier: Modifier = Modifier,
    selectedAction: (index: Int, value: String) -> Unit
) {

    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(defaultSelected) }

    // if the drop down is getting populated with different values...
    LaunchedEffect(dropDownList) {
        if (dropDownList.isNotEmpty() && selectedIndex >= 0) {
            //list changed but are selection is still in range.
            selectedAction(selectedIndex, dropDownList[selectedIndex])
        } else {
            //list changed drastically, reset all.
            selectedIndex = -1
            selectedAction(-1, "")
        }
    }

    //should never happen, but protecting as some crashes were getting reported.
    if (selectedIndex >= dropDownList.size) {
        selectedIndex = -1
        selectedAction(-1, "")
    }

    Box(
        modifier
            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 62.dp)
                .clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val mainLabel = if (selectedIndex < 0 || dropDownList.isEmpty()) {
                stringResource(id = dropDownLabel)
            } else {
                dropDownList[selectedIndex]
            }

            Column(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (selectedIndex > -1 && dropDownList.isNotEmpty()) {
                    Text(
                        text = stringResource(id = dropDownLabel),
//                        style = TextInput,
//                        color = ShotLanderTheme.colors.primaryText,
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = mainLabel,
//                    style = TextInput,
                    fontSize = 18.sp,
//                    color = ShotLanderTheme.colors.primaryText
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
//                tint = ShotLanderTheme.colors.componentForeground,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(end = 10.dp)
            )
        }

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(fraction = .80f)
//                .background(ShotLanderTheme.colors.defaultBackground)
                .requiredSizeIn(maxHeight = 250.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            dropDownList.forEachIndexed { position, label ->
                val optionalTitle = dropDownTitles?.getOrNull(position)

                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        expanded = false
                        selectedIndex = position
                        selectedAction(position, label)
                    }) {

                    ConstraintLayout(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        val (text, divider) = createRefs()

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 14.dp)
                                .constrainAs(text) {
                                    centerVerticallyTo(parent)
                                }
                        ) {
                            if (optionalTitle != null) {
                                Text(
                                    text = optionalTitle,
//                                    style = TextInput,
                                    fontSize = 12.sp,
//                                    color = ShotLanderTheme.colors.secondaryText,
                                    modifier = Modifier.padding(bottom = 2.dp)
                                )
                            }

                            Text(text = label)
                        }

                        Divider(
                            color = Color.Black,
                            modifier = Modifier.constrainAs(divider) {
                                top.linkTo(text.bottom, margin = 15.dp)
                            })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSLDropDown() {
    FunnyTheme {
        FunnyDropDown(
            dropDownLabel = R.string.test_string,
            dropDownList = listOf("Label 1", "Label 2", "Label 3"),
            selectedAction = { pos, text ->

            })
    }
}