import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blablafit.ContactoModel
import com.example.blablafit.R

class ContactosAdapter (private val mContacts: MutableList<ContactoModel>) : RecyclerView.Adapter<ContactosAdapter.ViewHolder>()
{
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val nameTextView = itemView.findViewById<TextView>(R.id.contact_name)
        val messageButton = itemView.findViewById<Button>(R.id.message_button)

    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Contact","on create view holder")
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.card_view, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ContactosAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val contact: ContactoModel = mContacts[position]
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText(contact.name)
        val button = viewHolder.messageButton
        Log.i("Contact","contacto: ${contact.name}")
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        Log.i("Contact","contacto: ${mContacts.size}")
        return mContacts.size
    }
}