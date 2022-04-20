
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.tanvir.training.todoappbatch04.R

class CustomAlertDialog(val callback: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Delete")
        //builder.setIcon(R.drawable.ic_baseline_delete_24)
        builder.setMessage("Are you sure to delete?")
        builder.setPositiveButton("Yes") { dialog, which ->
            callback()
        }
        builder.setNegativeButton("Cancel", null)
        return builder.create()
    }
}