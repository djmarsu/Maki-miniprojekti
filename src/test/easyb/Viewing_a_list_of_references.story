
description 'User can view added references'

scenario "User can view all added references", {
    given 'command make view references is selected'
 
    when 'there are references added'

    then 'a list of references is shown'
}
