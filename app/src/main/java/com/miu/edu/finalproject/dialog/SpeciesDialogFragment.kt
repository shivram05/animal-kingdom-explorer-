package com.miu.edu.finalproject.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.miu.edu.finalproject.R
import com.miu.edu.finalproject.data.model.Animal
import com.miu.edu.finalproject.data.model.Species
import com.miu.edu.finalproject.databinding.AnimalDialogBinding
import com.miu.edu.finalproject.databinding.SpeciesDialogBinding
import com.miu.edu.finalproject.utils.onErrorTextFieldValidator
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.NotEmpty

class SpeciesDialogFragment(private val itemClicked: OnAddSpeciesItemClicked) :
    AppCompatDialogFragment(), Validator.ValidationListener {

    private lateinit var binding: SpeciesDialogBinding
    private lateinit var validator: Validator

    @NotEmpty(message = "Name Field is required")
    lateinit var nameEditText: TextInputEditText


    @NotEmpty(message = "Description Field is required")
    lateinit var descriptionEditText: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SpeciesDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        * Initializing fields
        * */
        initializingFields()

        validator = Validator(this)
        validator.setValidationListener(this)

        binding.saveTextView.setOnClickListener {
            validator.validate()
        }

        binding.cancelTextView.setOnClickListener {
            dismiss()
        }
    }

    private fun initializingFields() {
        nameEditText = binding.nameEditText
        descriptionEditText = binding.speciesEditText
    }

    override fun onValidationSucceeded() {
        val name = nameEditText.text.toString()
        val description = descriptionEditText.text.toString()
        itemClicked.onSpeciesItemClick(
            Species(
                name, description
            ), dialog
        )
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        activity?.onErrorTextFieldValidator(errors)
    }
}

interface OnAddSpeciesItemClicked {
    fun onSpeciesItemClick(species: Species, dialog: Dialog?)
}