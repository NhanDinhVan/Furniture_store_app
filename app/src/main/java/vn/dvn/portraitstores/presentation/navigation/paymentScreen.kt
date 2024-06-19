package vn.dvn.portraitstores.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//
//@Composable
//fun PaymentScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Thông tin thanh toán",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        PaymentCard(
//            cardNumber = "**** **** **** 1234",
//            cardHolderName = "Nguyen Van A",
//            expiryDate = "12/24"
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        AmountDetails(
//            amount = "500,000 VND",
//            date = "17/06/2024"
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        PayButton {
//            // Handle pay button click
//        }
//    }
//}
//
//@Composable
//fun PaymentCard(cardNumber: String, cardHolderName: String, expiryDate: String) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(8.dp),
//        elevation = 4.dp
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Số thẻ: $cardNumber", fontSize = 16.sp)
//            Text(text = "Chủ thẻ: $cardHolderName", fontSize = 16.sp)
//            Text(text = "Hạn sử dụng: $expiryDate", fontSize = 16.sp)
//        }
//    }
//}
//
//@Composable
//fun AmountDetails(amount: String, date: String) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        shape = RoundedCornerShape(8.dp),
//        elevation = 4.dp
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = "Số tiền: $amount", fontSize = 16.sp)
//            Text(text = "Ngày thanh toán: $date", fontSize = 16.sp)
//        }
//    }
//}
//
//@Composable
//fun PayButton(onClick: () -> Unit) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier.fillMaxWidth()
//        .padding(top = 36.dp)
//    ) {
//        Text(text = "Thanh toán", fontSize = 18.sp)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//        PaymentScreen()
//}